package com.thoutube.controllers.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.thoutube.model.User;

public class UserDto {
    private Long id;
    private String name;
    private String email;

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
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

    public static List<UserDto> convert(List<User> user) {
        return user.stream().map(UserDto::new).collect(Collectors.toList());
    }

}