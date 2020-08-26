package com.thoutube.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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