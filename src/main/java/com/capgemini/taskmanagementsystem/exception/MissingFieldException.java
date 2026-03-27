package com.capgemini.taskmanagementsystem.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MissingFieldException extends Exception{
    private String message;
    public MissingFieldException(String message){
        this.message = message;
    }
}
