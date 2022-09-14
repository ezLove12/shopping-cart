package com.example.mock2project.exception;

public class ProductNotExistException extends IllegalArgumentException{

    public ProductNotExistException(String msg) {
        super(msg);
    }
}
