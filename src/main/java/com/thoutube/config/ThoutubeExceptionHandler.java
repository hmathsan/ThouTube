package com.thoutube.config;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.thoutube.services.exceptions.FileException;
import com.thoutube.services.exceptions.ObjectNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ThoutubeExceptionHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ObjectNotFoundException.class)
    public String objectNotFoundHandler(ObjectNotFoundException e) {
        return "Error 404: \nMessage: " + e.getMessage() ;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String notValidHandler(MethodArgumentNotValidException e) {
        return "Error 404: \nMessage: Field " + e.getBindingResult().getFieldError().getField().toString() + " must not be empty";
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public String missingRequestParamHandler(MissingServletRequestParameterException e) {
        return "Error 404: \nMessage: Parameter " + e.getParameterName().toString() + " must not be empty";
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(FileException.class)
    public String fileExceptionHandler(FileException e) {
        return "Error 404: \nMessage: " + e.getMessage().toString();
    }

    @ExceptionHandler(AmazonServiceException.class)
    public ResponseEntity<String> amazonServiceHandler(AmazonServiceException e) {
        HttpStatus status = HttpStatus.valueOf(e.getStatusCode());
        return ResponseEntity.status(status).body("Error 404: \nMessage: " + e.getMessage().toString());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AmazonClientException.class)
    public String amazonClientHandler(AmazonClientException e) {
        return "Error 404: \nMessage: " + e.getMessage().toString();
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AmazonS3Exception.class)
    public String amazonS3Handler(AmazonS3Exception e) {
        return "Error 404: \nMessage: " + e.getMessage().toString();
    }
}