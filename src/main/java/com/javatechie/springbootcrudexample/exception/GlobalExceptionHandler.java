package com.javatechie.springbootcrudexample.exception;

import com.javatechie.springbootcrudexample.entity.ApiError;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.NonNull;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return prepareResponse(HttpStatus.NOT_FOUND, "Resource Not Found", Optional.of(ex.getMessage()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(Exception ex) {
        return prepareResponse(HttpStatus.BAD_REQUEST, "Constraint violations", Optional.of(ex.getMessage()));
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(Exception ex) {
        return prepareResponse(HttpStatus.BAD_REQUEST, "Error occurred", Optional.of(ex.getLocalizedMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return prepareResponse(HttpStatus.BAD_REQUEST, "Malformed JSON request", Optional.ofNullable(ex.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return prepareResponse(HttpStatus.BAD_REQUEST, "Validation Errors", Optional.of(ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + " : " + error.getDefaultMessage())
                .toString()));
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return prepareResponse(HttpStatus.BAD_REQUEST, "Type Mismatch", Optional.ofNullable(ex.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return prepareResponse(HttpStatus.BAD_REQUEST, "Missing parameters", Optional.of(ex.getParameterName() + " parameter is missing"));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(mediaType -> builder.append(mediaType).append(","));
        return prepareResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Unsupported Media Type", Optional.of(builder.toString()));
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return prepareResponse(HttpStatus.NOT_FOUND, "Method Not Found", Optional.of(String.format("Could not find the %s method for URL %s", ex.getHttpMethod(), ex.getRequestURL())));
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return prepareResponse(HttpStatus.METHOD_NOT_ALLOWED, "Unsupported Method", Optional.of(ex.getMessage()));
    }

    private ResponseEntity<Object> prepareResponse(HttpStatus status, String message, Optional<String> detail) {
        List<String> details = new ArrayList<>();
        detail.ifPresent(details::add);
        ApiError apiError = new ApiError(LocalDateTime.now(), status, message, details);
        return ResponseEntityBuilder.build(apiError);
    }
}
