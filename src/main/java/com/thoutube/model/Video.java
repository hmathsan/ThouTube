package com.thoutube.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "videos")
public class Video {
    
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String videoUrl;
    @Column(nullable = false)
    private String thumbUrl;
    @Column(nullable = false)
    private LocalDateTime uploadDate = LocalDateTime.now();
    @ManyToOne
    private User author;
    @Column(nullable = false)
    private Long likes;
    @OneToMany(mappedBy = "video" ,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<VideoComments> videoComments = new ArrayList<>();

    public Video(){
    }

    public Video(String title, String videoUrl, String thumbUrl, User user) {
        this.title = title;
        this.videoUrl = videoUrl;
        this.thumbUrl = thumbUrl;
        this.author = user;
        this.likes = Long.valueOf(0);
    }

    public Video(Video video, String title) {
        this.id = video.getId();
        this.title = video.getTitle();
        this.uploadDate = video.getUploadDate();
        this.author = video.getAuthor();
        this.likes = video.getLikes() + 1;
        this.videoComments = video.getVideoComments();
        if(!title.equals("")) {
            this.title = title;
            this.likes = video.getLikes();
        }
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

    public String getVideoUrl() {
        return this.videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getThumbUrl() {
        return this.thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
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

    public List<VideoComments> getVideoComments() {
        return this.videoComments;
    }

    public void setVideoComments(List<VideoComments> videoComments) {
        this.videoComments = videoComments;
    }

}