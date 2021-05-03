package com.stackroute.restservicedemo.Exceptions;

public class UserAlradyExistsException extends Exception{
    private String message;
    public UserAlradyExistsException(String message) {
        super();
        this.message = message;
    }
    public UserAlradyExistsException() {
    }
}