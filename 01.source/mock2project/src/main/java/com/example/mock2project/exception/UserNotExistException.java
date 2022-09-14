package com.example.mock2project.exception;

public class UserNotExistException extends IllegalArgumentException{

    public UserNotExistException(String msg) {
        super(msg);
    }
}
