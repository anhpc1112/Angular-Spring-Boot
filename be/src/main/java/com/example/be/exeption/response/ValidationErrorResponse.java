package com.example.be.exeption.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationErrorResponse {
    private HttpStatus httpStatus;
    private String error;
    private String message;
}
