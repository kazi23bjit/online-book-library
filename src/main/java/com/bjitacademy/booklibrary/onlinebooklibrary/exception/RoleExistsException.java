package com.bjitacademy.booklibrary.onlinebooklibrary.exception;

public class RoleExistsException extends RuntimeException{
    public RoleExistsException(String message){
        super(message);
    }
}
