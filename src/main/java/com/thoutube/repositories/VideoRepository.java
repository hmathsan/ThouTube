package com.thoutube.repositories;

import java.util.List;

import com.thoutube.model.Video;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {

	List<Video> findByAuthorId(Long id);
    
}