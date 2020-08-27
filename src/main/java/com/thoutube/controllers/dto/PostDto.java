package com.thoutube.controllers.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.thoutube.model.Post;

public class PostDto {
    private Long id;
    private String title;
    private String message;
    private LocalDateTime postDate;
    private Long likes;

    public PostDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.message = post.getMessage();
        this.postDate = post.getPostDate();
        this.likes = post.getLikes();
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getMessage() {
        return this.message;
    }

    public LocalDateTime getPostDate() {
        return this.postDate;
    }

    public Long getLikes() {
        return this.likes;
    }

	public static List<PostDto> convert(List<Post> posts) {
		return posts.stream().map(PostDto::new).collect(Collectors.toList());
	}
    
}