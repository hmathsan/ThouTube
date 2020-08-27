package com.thoutube.controllers.dto;

import java.time.LocalDateTime;

import com.thoutube.model.Post;

import org.springframework.data.domain.Page;

public class DetailedPostDto {
    private Long id;
    private Long authorId;
    private String authorName;
    private String title;
    private String message;
    private LocalDateTime postDate;
    private Long likes;
    
    public DetailedPostDto(Post post) {
        this.id = post.getId();
        this.authorId = post.getAuthor().getId();
        this.authorName = post.getAuthor().getName();
        this.title = post.getTitle();
        this.message = post.getMessage();
        this.postDate = post.getPostDate();
        this.likes = post.getLikes();
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

    public static Page<DetailedPostDto> convert(Page<Post> posts) {
		return posts.map(DetailedPostDto::new);
	}
    
}