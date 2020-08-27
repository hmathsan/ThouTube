package com.thoutube.controllers.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.thoutube.model.User;

public class UserDto {
    private Long id;
    private String name;
    private String email;
    private Long totalPosts;
    private Long totalVideos;

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.totalPosts = user.getPosts().stream().count();
        this.totalVideos = user.getVideos().stream().count();
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

    public static List<UserDto> convert(List<User> user) {
        return user.stream().map(UserDto::new).collect(Collectors.toList());
    }

}