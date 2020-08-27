package com.thoutube.repositories;

import com.thoutube.model.Video;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {

	Page<Video> findByAuthorId(Long id, Pageable pagination);
    
}