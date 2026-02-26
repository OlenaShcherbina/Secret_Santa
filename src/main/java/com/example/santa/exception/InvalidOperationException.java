package com.example.santa.exception;

public class InvalidOperationException extends RuntimeException{
    public InvalidOperationException(String msg){
        super(msg);
    }
}
