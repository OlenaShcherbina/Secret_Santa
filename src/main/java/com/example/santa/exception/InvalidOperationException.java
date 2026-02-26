package com.example.santa.exception;

public class InvalidOperationException extends RuntimeException{
    InvalidOperationException(String msg){
        super(msg);
    }
}
