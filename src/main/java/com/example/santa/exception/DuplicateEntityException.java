package com.example.santa.exception;

public class DuplicateEntityException extends RuntimeException{
    public DuplicateEntityException(String msg){
        super(msg);
    }
}
