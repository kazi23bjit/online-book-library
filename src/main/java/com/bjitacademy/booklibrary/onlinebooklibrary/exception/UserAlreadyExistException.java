package com.bjitacademy.booklibrary.onlinebooklibrary.exception;

public class UserAlreadyExistException extends RuntimeException{
    public UserAlreadyExistException(String message){
        super(message);
    }
}
