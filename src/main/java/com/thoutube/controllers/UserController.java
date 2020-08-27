package com.thoutube.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.thoutube.controllers.dto.*;
import com.thoutube.controllers.form.*;
import com.thoutube.model.*;
import com.thoutube.repositories.*;

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

    @PostMapping("/{id}/video")
    @Transactional
    public ResponseEntity<VideoDto> uploadVideo(@PathVariable Long id, @RequestBody @Valid VideoForm form, UriComponentsBuilder uriBuilder) {
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()) {
            Video video = new Video(form.getTitle(), user.get());
            videoRepository.save(video);

            //URI not completed, need to finish VideoController
            URI uri = uriBuilder.build().toUri();
            return ResponseEntity.created(uri).build();
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/post")
    @Transactional
    public ResponseEntity<PostDto> newPost(@PathVariable Long id, @RequestBody @Valid PostForm form, UriComponentsBuilder uriBuilder) {
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()) {
            Post post = new Post(form.getTitle(), form.getMessage(), user.get());
            postRepository.save(post);

            //URI not completed, need to finish PostController
            URI uri = uriBuilder.build().toUri();
            return ResponseEntity.created(uri).build();
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/post")
    public List<DetailedPostDto> postByUserId(@PathVariable Long id) {
        List<Post> posts = postRepository.findByAuthorId(id);
        return DetailedPostDto.convert(posts);
    }

    @GetMapping("/{id}/video")
    public List<DetailedVideoDto> videoByUserId(@PathVariable Long id) {
        List<Video> videos = videoRepository.findByAuthorId(id);
        return DetailedVideoDto.convert(videos);
    }
}