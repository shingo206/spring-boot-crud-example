package com.javatechie.springbootcrudexample.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -5554702057444971321L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
