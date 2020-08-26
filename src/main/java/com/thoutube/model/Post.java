package com.thoutube.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "posts")
public class Post {
    
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private LocalDateTime postDate = LocalDateTime.now();
    private Long likes;
    @ManyToOne
    private User author;
    @OneToMany(mappedBy = "post", cascade = {CascadeType.PERSIST ,CascadeType.MERGE})
    private List<PostComments> postComments = new ArrayList<>();

    public Post() {
    }

    public Post(String title, String message, User user) {
        this.title = title;
        this.message = message;
        this.author = user;
        this.likes = Long.valueOf(0);
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

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getPostDate() {
        return this.postDate;
    }

    public void setPostDate(LocalDateTime postDate) {
        this.postDate = postDate;
    }

    public Long getLikes() {
        return this.likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public User getAuthor() {
        return this.author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<PostComments> getComments() {
        return this.postComments;
    }

    public void setComments(List<PostComments> comments) {
        this.postComments = comments;
    }

}