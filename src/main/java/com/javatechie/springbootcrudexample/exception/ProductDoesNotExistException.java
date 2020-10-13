package com.javatechie.springbootcrudexample.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The product does not exist.")
public class ProductDoesNotExistException extends RuntimeException {
    public ProductDoesNotExistException() {
        super("The product does not exist.");
    }
}
