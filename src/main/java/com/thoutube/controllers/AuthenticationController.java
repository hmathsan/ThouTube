package com.thoutube.controllers;

import javax.validation.Valid;

import com.thoutube.controllers.form.AuthForm;
import com.thoutube.services.AuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authService;

    @PostMapping
    public ResponseEntity<?> authenticate(@RequestBody @Valid AuthForm form) {
        return authService.authenticateUser(form);
    }
}