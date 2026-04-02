package com.capgemini.taskmanagementsystem.exception;

import com.capgemini.taskmanagementsystem.dto.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;


@RestControllerAdvice
public class GlobalExceptionHandler {


    // This class is used for Not found exception
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> HandleNotFoundException(ResourceNotFoundException exception, HttpServletRequest servletRequest){

       ErrorResponseDto responseDto = new ErrorResponseDto();
       responseDto.setApiPath(servletRequest.getRequestURI());
       responseDto.setErrorCode(HttpStatus.NOT_FOUND);
       responseDto.setErrorMessage(exception.getMessage());
       responseDto.setErrorTime(LocalDateTime.now());

       return new ResponseEntity<ErrorResponseDto>(responseDto,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponseDto> HandleUnauthorizedException(UnauthorizedException exception,HttpServletRequest servletRequest){
        ErrorResponseDto responseDto = new ErrorResponseDto();
        responseDto.setApiPath(servletRequest.getRequestURI());
        responseDto.setErrorCode(HttpStatus.UNAUTHORIZED);
        responseDto.setErrorMessage(exception.getMessage());
        responseDto.setErrorTime(LocalDateTime.now());

        return new ResponseEntity<ErrorResponseDto>(responseDto,HttpStatus.UNAUTHORIZED);
    }

    // This class is used for Missing Field
    @ExceptionHandler(MissingFieldException.class)
    public ResponseEntity<ErrorResponseDto> HandleMissingFieldException(MissingFieldException exception,HttpServletRequest servletRequest){
        ErrorResponseDto responseDto = new ErrorResponseDto();
        responseDto.setErrorMessage(exception.getMessage());
        responseDto.setErrorTime(LocalDateTime.now());
        responseDto.setApiPath(servletRequest.getRequestURI());
        responseDto.setErrorCode(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<ErrorResponseDto>(responseDto,HttpStatus.BAD_REQUEST);
    }


    // This class is used for Wrong URL
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponseDto> HandleUrlNotPresent(NoHandlerFoundException exception,HttpServletRequest servletRequest){
        ErrorResponseDto responseDto = new ErrorResponseDto();
        responseDto.setErrorMessage(exception.getMessage());
        responseDto.setErrorTime(LocalDateTime.now());
        responseDto.setApiPath(servletRequest.getRequestURI());
        responseDto.setErrorCode(HttpStatus.BAD_GATEWAY);

        return new ResponseEntity<ErrorResponseDto>(responseDto,HttpStatus.BAD_GATEWAY);
    }

    // This class is used for Wrong Mapping Method
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponseDto> HandleWrongMapping(HttpRequestMethodNotSupportedException exception,HttpServletRequest servletRequest){
        ErrorResponseDto responseDto = new ErrorResponseDto();
        responseDto.setErrorMessage(exception.getMessage());
        responseDto.setErrorTime(LocalDateTime.now());
        responseDto.setApiPath(servletRequest.getRequestURI());
        responseDto.setErrorCode(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<ErrorResponseDto>(responseDto,HttpStatus.BAD_REQUEST);
    }


    // This class is used for unknown exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> HandleUnknownException(Exception exception,HttpServletRequest servletRequest){
        ErrorResponseDto responseDto = new ErrorResponseDto();
        responseDto.setErrorMessage(exception.getMessage());
        responseDto.setErrorTime(LocalDateTime.now());
        responseDto.setApiPath(servletRequest.getRequestURI());
        responseDto.setErrorCode(HttpStatus.EXPECTATION_FAILED);

        return new ResponseEntity<ErrorResponseDto>(responseDto,HttpStatus.EXPECTATION_FAILED);
    }

}
