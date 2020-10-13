package com.javatechie.springbootcrudexample.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductDoesNotExistException extends RuntimeException {
    public ProductDoesNotExistException() {
        super("The product code does not exist.");
    }
}
