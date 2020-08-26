package com.thoutube.controllers;

import java.util.List;

import com.thoutube.controllers.dto.DetailedUserDto;
import com.thoutube.model.User;
import com.thoutube.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<DetailedUserDto> index() {
        List<User> user = userRepository.findAll();
        return DetailedUserDto.convert(user);
    }


}