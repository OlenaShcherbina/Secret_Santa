package com.example.santa.exception;

public class DuplicateEntityException extends RuntimeException{
    DuplicateEntityException(String msg){
        super(msg);
    }
}
