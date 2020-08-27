package com.thoutube.config;

import com.thoutube.services.exceptions.ObjectNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ThoutubeExceptionHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ObjectNotFoundException.class)
    public String ObjectNotFoundHandler(ObjectNotFoundException e) {
        return "Error 404: \nMessage: " + e.getMessage() ;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String NotValidHandler(MethodArgumentNotValidException e) {
        return "Error 404: \nMessage: Field " + e.getBindingResult().getFieldError().getField().toString() + " must not be empty";
    }

}