package com.capgemini.taskmanagementsystem.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MissingFieldException extends RuntimeException{
    private String message;
    public MissingFieldException(String message){
        this.message = message;
    }
}
