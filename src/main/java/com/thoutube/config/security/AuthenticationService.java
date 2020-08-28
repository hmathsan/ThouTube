package com.thoutube.config.security;

import java.util.Optional;

import com.thoutube.model.User;
import com.thoutube.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userObj = userRepository.findByEmail(username);
        return userObj.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }
    
}