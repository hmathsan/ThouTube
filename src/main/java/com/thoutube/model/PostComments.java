package com.thoutube.model;

import javax.persistence.*;

import com.thoutube.controllers.form.CommentForm;

@Entity
@Table(name = "post_comments")
public class PostComments {
    
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User author;
    private String message;
    @ManyToOne
    private Post post;

    public PostComments(CommentForm form, User user, Post post) {
        this.author = user;
        this.message = form.getMessage();
        this.post = post;
    }

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

    public Post getPost() {
        return this.post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

}