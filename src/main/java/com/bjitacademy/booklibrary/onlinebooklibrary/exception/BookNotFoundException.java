package com.bjitacademy.booklibrary.onlinebooklibrary.exception;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(String message){
        super(message);
    }
}
