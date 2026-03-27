package com.capgemini.taskmanagementsystem.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourseNotFoundException extends RuntimeException{
    private String message;
    public ResourseNotFoundException(String message){
        this.message = message;
    }

}
