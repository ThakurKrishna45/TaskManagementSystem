package com.capgemini.taskmanagementsystem.exception.userrole;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(String message) {
        super(message);
    }
}