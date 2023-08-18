package com.example.be.exeption;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class BusinessException extends Exception {
    private final String causeId;
}
