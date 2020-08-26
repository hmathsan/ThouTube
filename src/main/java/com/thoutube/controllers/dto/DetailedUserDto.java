package com.thoutube.controllers.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.thoutube.model.User;

public class DetailedUserDto {
    private Long id;
    private String name;
    private String email;
    private List<PostDto> posts;
    private List<VideoDto> videos;

    public DetailedUserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.posts = new ArrayList<>();
        this.videos = new ArrayList<>();
        this.posts.addAll(user.getPosts().stream().map(PostDto::new).collect(Collectors.toList()));
        this.videos.addAll(user.getVideos().stream().map(VideoDto::new).collect(Collectors.toList()));
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

    public List<PostDto> getPosts() {
        return this.posts;
    }

    public List<VideoDto> getVideos() {
        return this.videos;
    }

    public static List<DetailedUserDto> convert(List<User> user) {
        return user.stream().map(DetailedUserDto::new).collect(Collectors.toList());
    }

}
