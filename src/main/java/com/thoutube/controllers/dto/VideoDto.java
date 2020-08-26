package com.thoutube.controllers.dto;

import java.time.LocalDateTime;

import com.thoutube.model.Video;

public class VideoDto {
    private Long id;
    private String title;
    private LocalDateTime uploadDate;
    private Long likes;

    public VideoDto(Video video) {
        this.id = video.getId();
        this.title = video.getTitle();
        this.uploadDate = video.getUploadDate();
        this.likes = video.getLikes();
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public LocalDateTime getUploadDate() {
        return this.uploadDate;
    }

    public Long getLikes() {
        return this.likes;
    }

}