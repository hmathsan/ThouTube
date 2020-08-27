package com.thoutube.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.thoutube.controllers.dto.DetailedPostDto;
import com.thoutube.controllers.form.PostForm;
import com.thoutube.model.Post;
import com.thoutube.repositories.PostRepository;
import com.thoutube.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServices {
    
    @Autowired
    private PostRepository postRepository;

    public List<DetailedPostDto> detailedIndex() {
        List<Post> posts = postRepository.findAll();
        return DetailedPostDto.convert(posts);
    }

    public Post index(Long id) {
        Optional<Post> postObj = postRepository.findById(id);
        return postObj.orElseThrow(() -> new ObjectNotFoundException(exceptionMsg(id)));
    }

    @Transactional
    public DetailedPostDto update(Long id, PostForm form) {
        Optional<Post> postObj = postRepository.findById(id);
        Post post = postObj.orElseThrow(() -> new ObjectNotFoundException(exceptionMsg(id)));

        Post updatedPost = new Post(post, form.getTitle(), form.getMessage());
        postRepository.save(updatedPost);

        return new DetailedPostDto(updatedPost);
    }

    public String exceptionMsg(Long id) {
        return ("Object of ID " + id + " not found");
    }
}