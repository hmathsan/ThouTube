package com.thoutube.controllers;

import java.net.URI;

import javax.validation.Valid;

import com.thoutube.controllers.dto.DetailedPostDto;
import com.thoutube.controllers.dto.DetailedUserDto;
import com.thoutube.controllers.dto.DetailedVideoDto;
import com.thoutube.controllers.dto.PostDto;
import com.thoutube.controllers.dto.VideoDto;
import com.thoutube.controllers.form.CommentForm;
import com.thoutube.controllers.form.PasswordForm;
import com.thoutube.controllers.form.PostForm;
import com.thoutube.controllers.form.VideoForm;
import com.thoutube.model.User;
import com.thoutube.services.UserServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    private UserServices userService;

    @GetMapping
    public ResponseEntity<Page<DetailedUserDto>> detailedIndex(@RequestParam int page,
                @RequestParam int pageSize, @RequestParam String orderBy) {
        Page<DetailedUserDto> user = userService.detailedIndex(page, pageSize, orderBy);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public ResponseEntity<DetailedUserDto> create(@RequestParam(required = true, name = "name") String name, 
        @RequestParam(required = true, name = "email") String email,
        @RequestParam(required = true, name = "password") String password,
        @RequestParam(name = "file", required = false) MultipartFile file, UriComponentsBuilder uriBuilder) {
        System.out.println(file);
        DetailedUserDto user = userService.create(name, email, password, file);

        URI uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailedUserDto> index(@PathVariable Long id) {
        User user = userService.index(id);
        return ResponseEntity.ok().body(new DetailedUserDto(user));
    }

    @PostMapping("/{id}")
    public ResponseEntity<DetailedUserDto> newComment(@PathVariable Long id, @RequestParam String type,
            @RequestBody @Valid CommentForm form, UriComponentsBuilder uriBuilder) {
        DetailedUserDto user = userService.newComment(id, type, form);

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
    public ResponseEntity<Page<DetailedPostDto>> postByUserId(@PathVariable Long id,
        @RequestParam int page, @RequestParam int pageSize, @RequestParam String orderBy) {
        Page<DetailedPostDto> posts = userService.getPostByUserId(id, page, pageSize, orderBy);
        return ResponseEntity.ok().body(posts);
    }

    @GetMapping("/{id}/video")
    public ResponseEntity<Page<DetailedVideoDto>> videoByUserId(@PathVariable Long id,
        @RequestParam int page, @RequestParam int pageSize, @RequestParam String orderBy) {
        Page<DetailedVideoDto> videos = userService.getVideoByUserId(id, page, pageSize, orderBy);
        return ResponseEntity.ok().body(videos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetailedUserDto> updatePassword(@PathVariable Long id, @RequestBody(required = true) @Valid PasswordForm password) {
        DetailedUserDto user = userService.updateUserPassword(id, password);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/{id}/pictures")
    public ResponseEntity<Void> updateProfilePic(@PathVariable Long id, @RequestParam(name = "file") MultipartFile file) {
        URI uri = userService.uploadProfilePic(id, file);
        return ResponseEntity.created(uri).build();
    }
}