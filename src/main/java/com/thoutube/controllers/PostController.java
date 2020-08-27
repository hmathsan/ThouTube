package com.thoutube.controllers;

import java.util.List;
import java.util.Optional;

import com.thoutube.controllers.dto.DetailedPostDto;
import com.thoutube.controllers.form.PostForm;
import com.thoutube.model.Post;
import com.thoutube.repositories.PostRepository;

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
    private PostRepository postRepository;

    @GetMapping
    public List<DetailedPostDto> detailedIndex() {
        List<Post> posts = postRepository.findAll();
        return DetailedPostDto.convert(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailedPostDto> index(@PathVariable Long id) {
        Optional<Post> post = postRepository.findById(id);

        if(post.isPresent()) {
            return ResponseEntity.ok(new DetailedPostDto(post.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetailedPostDto> updateLikes(@PathVariable Long id) {
        Optional<Post> post = postRepository.findById(id);

        if(post.isPresent()) {
            Post updatedPost = new Post(post.get(), "", "");
            postRepository.save(updatedPost);
            return ResponseEntity.ok(new DetailedPostDto(updatedPost));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<DetailedPostDto> updatePostInfo(@PathVariable Long id, @RequestBody PostForm form) {
        Optional<Post> post = postRepository.findById(id);

        if(post.isPresent()) {
            Post updatedPost = new Post(post.get(), form.getTitle(), form.getMessage());
            postRepository.save(updatedPost);
            return ResponseEntity.ok(new DetailedPostDto(updatedPost));
        }

        return ResponseEntity.notFound().build();
    }
}