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
    @ResponseBody
    @ExceptionHandler(ResourseNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponseDto> HandleNotFoundException(ResourseNotFoundException exception, HttpServletRequest servletRequest){

       ErrorResponseDto responseDto = new ErrorResponseDto();
       responseDto.setApiPath(servletRequest.getRequestURI());
       responseDto.setErrorCode(HttpStatus.NOT_FOUND);
       responseDto.setErrorMessage(exception.getMessage());
       responseDto.setErrorTime(LocalDateTime.now());

       return new ResponseEntity<ErrorResponseDto>(responseDto,HttpStatus.NOT_FOUND);
    }

    // This class is used for Login details or unauthorized details
    @ResponseBody
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorResponseDto> HandleUnauthorizedException(UnauthorizedException exception,HttpServletRequest servletRequest){
        ErrorResponseDto responseDto = new ErrorResponseDto();
        responseDto.setApiPath(servletRequest.getRequestURI());
        responseDto.setErrorCode(HttpStatus.UNAUTHORIZED);
        responseDto.setErrorMessage(exception.getMessage());
        responseDto.setErrorTime(LocalDateTime.now());

        return new ResponseEntity<ErrorResponseDto>(responseDto,HttpStatus.UNAUTHORIZED);
    }

    // This class is used for Missing Field
    @ResponseBody
    @ExceptionHandler(MissingFieldException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseDto> HandleMissingFieldException(UnauthorizedException exception,HttpServletRequest servletRequest){
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
