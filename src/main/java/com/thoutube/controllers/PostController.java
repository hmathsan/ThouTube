package com.thoutube.controllers;

import java.util.List;

import com.thoutube.controllers.dto.DetailedPostDto;
import com.thoutube.controllers.form.PostForm;
import com.thoutube.model.Post;
import com.thoutube.services.PostServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostController {
    
    @Autowired
    private PostServices postServices;

    @GetMapping
    public ResponseEntity<List<DetailedPostDto>> detailedIndex() {
        List<DetailedPostDto> posts = postServices.detailedIndex();
        return ResponseEntity.ok().body(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailedPostDto> index(@PathVariable Long id) {
        Post post = postServices.index(id);
        return ResponseEntity.ok().body(new DetailedPostDto(post));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetailedPostDto> updateLikes(@PathVariable Long id) {
        PostForm form = new PostForm("", "");
        DetailedPostDto post = postServices.update(id, form);
        return ResponseEntity.ok().body(post);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<DetailedPostDto> updatePostInfo(@PathVariable Long id, @RequestBody PostForm form) {
        DetailedPostDto post = postServices.update(id, form);
        return ResponseEntity.ok().body(post);
    }
}