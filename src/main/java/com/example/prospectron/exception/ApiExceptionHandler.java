package com.example.prospectron.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice   //mark class as exception handler
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class})  //mark this function as responsible for handling exception
    public ResponseEntity<Object> handleRequestException(ApiRequestException e){
        //exception payload
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(
                e.getMessage(),
                badRequest
        );
        
        return new ResponseEntity<>(apiException, badRequest);
    }
}
