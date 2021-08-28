package com.microexam.classapi.exceptions;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Optional;
import java.util.stream.Collectors;

@ControllerAdvice(basePackages = "com.microexam.classapi.controllers")
public class GlobalExceptionHandler  {

    @ExceptionHandler(value = {ClassNotFoundException.class, MethodArgumentNotValidException.class, ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleBadRequests(Exception e){
        StringBuilder builder = new StringBuilder();
        if(e instanceof ClassNotFoundException){
            builder.append(e.getMessage());
        }
        else if(e instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException)  e;
            builder.append(ex.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", ")));
        }
        else if(e instanceof ConstraintViolationException){
            ConstraintViolationException ex = (ConstraintViolationException) e;
            builder.append(ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(", ")));
        }
        return generateError(HttpStatus.BAD_REQUEST,builder.toString());
    }
    @ExceptionHandler(value = {ClassException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNotFoundRequests(Exception e){
        return generateError(HttpStatus.NOT_FOUND, e.getMessage());
    }
    private ResponseEntity<String> generateError(final HttpStatus httpStatus, final String errorMessage) {
        final String message = Optional.ofNullable(errorMessage).orElse("");
        return ResponseEntity.status(httpStatus).body(message);
    }
}
