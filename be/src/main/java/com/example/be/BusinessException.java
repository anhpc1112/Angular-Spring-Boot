package com.example.be;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BusinessException {
    private String message;
    private String code;
}
