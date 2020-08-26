package com.thoutube.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "videos")
public class Video {
    
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private LocalDateTime uploadDate = LocalDateTime.now();
    @ManyToOne
    private User author;
    private Long likes;
    @ManyToMany
    private List<VideoComments> videoComments;

    public Video(){
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User getAuthor() {
        return this.author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Long getLikes() {
        return this.likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public List<VideoComments> getComments() {
        return this.videoComments;
    }

    public void setComments(List<VideoComments> comments) {
        this.videoComments = comments;
    }

}