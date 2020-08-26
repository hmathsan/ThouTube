package com.thoutube.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.thoutube.controllers.dto.DetailedUserDto;
import com.thoutube.controllers.dto.UserDto;
import com.thoutube.controllers.form.CommentForm;
import com.thoutube.controllers.form.UserForm;
import com.thoutube.model.Post;
import com.thoutube.model.PostComments;
import com.thoutube.model.User;
import com.thoutube.model.Video;
import com.thoutube.model.VideoComments;
import com.thoutube.repositories.PostCommentsRepository;
import com.thoutube.repositories.PostRepository;
import com.thoutube.repositories.UserRepository;
import com.thoutube.repositories.VideoCommentsRepository;
import com.thoutube.repositories.VideoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private PostCommentsRepository postCommentsRepository;
    @Autowired
    private VideoCommentsRepository videoCommentsRepository;

    @GetMapping
    public List<DetailedUserDto> detailedIndex() {
        List<User> user = userRepository.findAll();
        return DetailedUserDto.convert(user);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<UserDto> create(@RequestBody @Valid UserForm form, UriComponentsBuilder uriBuilder) {
        User user = new User(form.getName(), form.getEmail(), form.getPassword());
        userRepository.save(user);

        URI uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserDto(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailedUserDto> index(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            return ResponseEntity.ok(new DetailedUserDto(user.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/{type}")
    @Transactional
    public ResponseEntity<UserDto> newComment(@PathVariable Long id, @PathVariable String type, @RequestBody @Valid CommentForm form, UriComponentsBuilder uriBuilder) {
        Optional<User> user = userRepository.findById(id);
        Optional<Post> post = postRepository.findById(form.getPostId());
        Optional<Video> video = videoRepository.findById(form.getPostId());

        if(type.equals("POST")) {
            if(user.isPresent() && post.isPresent()){
                PostComments comment = new PostComments(form, user.get(), post.get());
                postCommentsRepository.save(comment);

                URI uri = uriBuilder.path("/users/{id}").buildAndExpand(user.get().getId()).toUri();
                return ResponseEntity.created(uri).build();
            }
        } else if(type.equals("VIDEO")) {
            if(user.isPresent() && video.isPresent()) {
                VideoComments comment = new VideoComments(form, user.get(), video.get());
                videoCommentsRepository.save(comment);

                URI uri = uriBuilder.path("/users/{id}").buildAndExpand(user.get().getId()).toUri();
                return ResponseEntity.created(uri).build();
            }
        }

        return ResponseEntity.notFound().build();
    }
}