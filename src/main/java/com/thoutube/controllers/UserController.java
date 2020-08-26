package com.thoutube.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.thoutube.controllers.dto.DetailedUserDto;
import com.thoutube.controllers.dto.UserDto;
import com.thoutube.controllers.form.UserForm;
import com.thoutube.model.User;
import com.thoutube.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<DetailedUserDto> detailedIndex() {
        List<User> user = userRepository.findAll();
        return DetailedUserDto.convert(user);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<UserDto> create(@RequestBody @Valid UserForm form, UriComponentsBuilder uriBuilder) {
        User user = new User(form.getName(), form.getEmail(), form.getPassword());
        userRepository.save(user);

        URI uri = uriBuilder.path("/users").build().toUri();
        return ResponseEntity.created(uri).body(new UserDto(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailedUserDto> index(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            return ResponseEntity.ok(new DetailedUserDto(user.get()));
        }
        return ResponseEntity.notFound().build();
    }
}