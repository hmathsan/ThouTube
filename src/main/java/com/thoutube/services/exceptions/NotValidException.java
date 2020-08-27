package com.thoutube.services.exceptions;

public class NotValidException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public NotValidException(String msg) {
        super(msg);
    }
}