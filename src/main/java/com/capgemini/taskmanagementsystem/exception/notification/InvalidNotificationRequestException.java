package com.capgemini.taskmanagementsystem.exception.notification;

public class InvalidNotificationRequestException extends RuntimeException {
    public InvalidNotificationRequestException(String message) {
        super(message);
    }
}
