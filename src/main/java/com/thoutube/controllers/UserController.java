package com.thoutube.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.thoutube.controllers.dto.DetailedPostDto;
import com.thoutube.controllers.dto.DetailedUserDto;
import com.thoutube.controllers.dto.DetailedVideoDto;
import com.thoutube.controllers.dto.PostDto;
import com.thoutube.controllers.dto.UserDto;
import com.thoutube.controllers.dto.VideoDto;
import com.thoutube.controllers.form.CommentForm;
import com.thoutube.controllers.form.PasswordForm;
import com.thoutube.controllers.form.PostForm;
import com.thoutube.controllers.form.UserForm;
import com.thoutube.controllers.form.VideoForm;
import com.thoutube.model.User;
import com.thoutube.services.ThoutubeServices;
import com.thoutube.services.UserServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private ThoutubeServices amazonServices;
    @Autowired
    private UserServices userService;

    @GetMapping
    public ResponseEntity<List<DetailedUserDto>> detailedIndex() {
        List<DetailedUserDto> user = userService.detailedIndex();
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody @Valid UserForm form, UriComponentsBuilder uriBuilder) {
        UserDto user = userService.create(form);

        URI uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailedUserDto> index(@PathVariable Long id) {
        User user = userService.index(id);
        return ResponseEntity.ok().body(new DetailedUserDto(user));
    }

    @PostMapping("/{id}")
    public ResponseEntity<UserDto> newComment(@PathVariable Long id, @RequestParam String type,
            @RequestBody @Valid CommentForm form, UriComponentsBuilder uriBuilder) {
        UserDto user = userService.newComment(id, type, form);

        URI uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @PostMapping("/{id}/video")
    public ResponseEntity<VideoDto> uploadVideo(@PathVariable Long id, @RequestBody @Valid VideoForm form, UriComponentsBuilder uriBuilder) {
        VideoDto video = userService.uploadVideo(id, form);

        URI uri = uriBuilder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
        return ResponseEntity.created(uri).body(video);
    }

    @PostMapping("/{id}/post")
    public ResponseEntity<PostDto> newPost(@PathVariable Long id, @RequestBody @Valid PostForm form, UriComponentsBuilder uriBuilder) {
        PostDto post = userService.newPost(id, form);

        URI uri = uriBuilder.path("/posts/{id}").buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(uri).body(post);
    }

    @GetMapping("/{id}/post")
    public ResponseEntity<List<DetailedPostDto>> postByUserId(@PathVariable Long id) {
        List<DetailedPostDto> posts = userService.getPostByUserId(id);
        return ResponseEntity.ok().body(posts);
    }

    @GetMapping("/{id}/video")
    public ResponseEntity<List<DetailedVideoDto>> videoByUserId(@PathVariable Long id) {
        List<DetailedVideoDto> videos = userService.getVideoByUserId(id);
        return ResponseEntity.ok().body(videos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updatePassword(@PathVariable Long id, @RequestBody(required = true) @Valid PasswordForm password) {
        UserDto user = userService.updateUserPassword(id, password);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/pictures")
    public ResponseEntity<Void> updateProfilePic(@RequestParam(name = "file") MultipartFile file) {
        URI uri = amazonServices.uploadProfilePic(file);
        return ResponseEntity.created(uri).build();
    }
}