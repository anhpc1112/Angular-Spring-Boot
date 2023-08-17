package com.example.be.exeption;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BusinessException {
    private String message;
    private String code;
}
