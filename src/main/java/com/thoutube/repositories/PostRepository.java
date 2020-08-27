package com.thoutube.repositories;

import java.util.List;

import com.thoutube.model.Post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findByAuthorId(Long id);
    
}