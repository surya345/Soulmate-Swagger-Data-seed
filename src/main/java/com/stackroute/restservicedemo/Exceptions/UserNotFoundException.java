package com.stackroute.restservicedemo.Exceptions;

public class UserNotFoundException extends Exception{
    private String message;
    public UserNotFoundException() {
    }
    public UserNotFoundException(String message) {
        super();
        this.message = message;
    }
}