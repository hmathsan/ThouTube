package com.thoutube.controllers.dto;

import com.thoutube.model.User;

import org.springframework.data.domain.Page;

public class DetailedUserDto {
    private Long id;
    private String name;
    private String email;
    private String imageUrl;
    private Long totalPosts;
    private Long totalVideos;

    public DetailedUserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.imageUrl = user.getImageUrl();
        if(user.getPosts() == null && user.getVideos() == null) {
            this.totalPosts = Long.valueOf(0);
            this.totalVideos = Long.valueOf(0);
        } else {
            this.totalPosts = user.getPosts().stream().count();
            this.totalVideos = user.getVideos().stream().count();
        }
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public Long getTotalPosts() {
        return this.totalPosts;
    }

    public Long getTotalVideos() {
        return this.totalVideos;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public static Page<DetailedUserDto> convert(Page<User> user) {
        return user.map(DetailedUserDto::new);
    }

}
