package com.thoutube.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "video_comments")
public class VideoComments {
    
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User author;
    private String message;
    @ManyToOne
    private Video video;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return this.author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Video getVideo() {
        return this.video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

}