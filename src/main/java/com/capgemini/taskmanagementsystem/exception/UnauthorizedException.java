package com.capgemini.taskmanagementsystem.exception;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnauthorizedException extends Exception{
    private String message;
    public UnauthorizedException(String message){
        this.message = message;
    }
}
