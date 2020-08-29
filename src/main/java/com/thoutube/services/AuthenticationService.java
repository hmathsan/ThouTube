package com.thoutube.services;

import java.util.Date;
import java.util.Optional;

import com.thoutube.controllers.dto.TokenDto;
import com.thoutube.controllers.form.AuthForm;
import com.thoutube.model.User;
import com.thoutube.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Value("${thoutube.jwt.expiration}")
    private String expiration;
    @Value("${thoutube.jwt.secret}")
    private String secret;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userObj = userRepository.findByEmail(username);
        return userObj.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

    public ResponseEntity<?> authenticateUser(AuthForm form) {
        UsernamePasswordAuthenticationToken loginForm = form.convert();

        try {
            Authentication authentication = authenticationManager.authenticate(loginForm);
            String token = generateToken(authentication) ;
            return ResponseEntity.ok(new TokenDto(token, getExpDate(new Date()), "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public String generateToken(Authentication auth) {
        User user = (User) auth.getPrincipal();
        Date today = new Date();
        Date expDate = getExpDate(today);
        return Jwts.builder().setIssuer("Thoutube API")
                    .setSubject(user.getId().toString())
                    .setIssuedAt(today).setExpiration(expDate)
                    .signWith(SignatureAlgorithm.HS256, secret)
                    .compact();
    }

    public Date getExpDate(Date currentTime) {
        return new Date(currentTime.getTime() + Integer.parseInt(expiration));
    }
    
}