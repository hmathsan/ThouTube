package com.thoutube.config.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thoutube.model.User;
import com.thoutube.repositories.UserRepository;
import com.thoutube.services.AuthenticationService;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class AuthenticationFilter extends OncePerRequestFilter {

    private AuthenticationService authService;
    private UserRepository userRepository;

    public AuthenticationFilter(AuthenticationService authService, UserRepository userRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String token = retrieveToke(request);
        boolean isValid = authService.isTokenValid(token);
        
        if(isValid) {
            authenticateClient(token);
        }

        filterChain.doFilter(request, response);
    }

    private void authenticateClient(String token) {
        Long idUser = authService.getUserId(token);
        Optional<User> user = userRepository.findById(idUser);

        UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(user.get(), null, user.get().getAuthorities());
        
        SecurityContextHolder.getContext().setAuthentication(userAuth);
    }

    private String retrieveToke(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7, token.length());
    }
    
}