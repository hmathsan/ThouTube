package com.thoutube.controllers.dto;

import java.time.LocalDateTime;

import com.thoutube.model.Video;

import org.springframework.data.domain.Page;

public class DetailedVideoDto {
    private Long id;
    private Long authorId;
    private String authorName;
    private String title;
    private LocalDateTime uploadDate;
    private Long likes;

    public DetailedVideoDto(Video video) {
        this.id = video.getId();
        this.authorId = video.getAuthor().getId();
        this.authorName = video.getAuthor().getName();
        this.title = video.getTitle();
        this.uploadDate = video.getUploadDate();
        this.likes = video.getLikes();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return this.authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getUploadDate() {
        return this.uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Long getLikes() {
        return this.likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }
    
    public static Page<DetailedVideoDto> convert(Page<Video> videos) {
		return videos.map(DetailedVideoDto::new);
	}
}