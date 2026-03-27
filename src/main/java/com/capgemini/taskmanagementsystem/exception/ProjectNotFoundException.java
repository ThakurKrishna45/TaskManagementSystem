package com.capgemini.taskmanagementsystem.exception;

public class ProjectNotFoundException extends RuntimeException{
    String message;
    public ProjectNotFoundException(String message){
        this.message = message;
    }
}
