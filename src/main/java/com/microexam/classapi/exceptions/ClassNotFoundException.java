package com.microexam.classapi.exceptions;

public class ClassNotFoundException extends RuntimeException {
    public ClassNotFoundException(String message){
        super(message);
    }
}
