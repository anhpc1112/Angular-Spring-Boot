package com.example.be.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessagePropertiesConstants {
    public static final String RESPONSE_VALIDATION_400 = "response.message.error.validation.400";
    public static final String RESPONSE_400 = "response.message.error.400";
    public static final String RESPONSE_401 = "response.message.error.401";
    public static final String RESPONSE_403 = "response.message.error.403";
    public static final String RESPONSE_404 = "response.message.error.404";
    public static final String RESPONSE_409 = "response.message.error.409";
    public static final String RESPONSE_500 = "response.message.error.500";
}
