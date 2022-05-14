package com.example.newdiaryapp.exceptions;

public class UserNotFoundException extends DiaryAppApplicationException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
