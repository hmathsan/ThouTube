package com.thoutube.repositories;

import com.thoutube.model.VideoComments;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoCommentsRepository extends JpaRepository<VideoComments, Long> {
    
}