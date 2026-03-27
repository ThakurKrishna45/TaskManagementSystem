package com.capgemini.taskmanagementsystem.exception.notification;

public class NotificationNotFoundException extends RuntimeException{
    public NotificationNotFoundException(String message) {
        super(message);
    }
}

/*
 * for Global Exception Handler

    // 1. Handle Resource Not Found (Generic or Specific)
    @ExceptionHandler({NotificationNotFoundException.class, RoleNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleNotFound(RuntimeException ex) {
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Resource Not Found",
                ""
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    *
    *
    // 2. Handle Bad Input (Missing fields, wrong data)
    @ExceptionHandler(InvalidNotificationRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(InvalidNotificationRequestException ex) {
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    *
    *
    // 3. Catch-all for any other DB or Server Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                "Kuch toh gadbad hai: " + ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
 */