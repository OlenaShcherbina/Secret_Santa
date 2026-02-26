package com.example.santa.exception;

public class NotFoundException extends RuntimeException{
    NotFoundException(String msg){
        super(msg);
    }
}
