package com.example.be.exeption;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ValidationException extends RuntimeException {
    private Map<String, List<String>> errors = new HashMap<>();

    private List<String> errorMessages = new ArrayList<>();

    public ValidationException(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public ValidationException(String errorMessage) {
        this.errorMessages = Arrays.asList(errorMessage);
    }

}
