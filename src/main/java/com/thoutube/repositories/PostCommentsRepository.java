package com.thoutube.repositories;

import com.thoutube.model.PostComments;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentsRepository extends JpaRepository<PostComments, Long> {
    
}