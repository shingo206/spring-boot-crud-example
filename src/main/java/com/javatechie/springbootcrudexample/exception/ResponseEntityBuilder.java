package com.javatechie.springbootcrudexample.exception;

import com.javatechie.springbootcrudexample.entity.ApiError;
import org.springframework.http.ResponseEntity;

public class ResponseEntityBuilder {
    public static ResponseEntity<Object> build(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
