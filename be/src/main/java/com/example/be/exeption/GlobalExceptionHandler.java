package com.example.be.exeption;

import com.example.be.exeption.response.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ValidationErrorResponse> handleException(Exception ex){
        ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex.getMessage());
        return new ResponseEntity<>(validationErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
