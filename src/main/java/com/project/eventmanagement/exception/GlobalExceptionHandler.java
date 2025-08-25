package com.project.eventmanagement.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<ErrorResponse>handleEventNotFound(Exception ex){


        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage(), new Date());

        return new  ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);



    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse>handleException(Exception ex){


        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage(), new Date());

        return new  ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);



    }

}
