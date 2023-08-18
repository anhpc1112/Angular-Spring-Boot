package com.example.be.exeption.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class BusinessErrorResponse {
    @JsonProperty("result")
    private final String result;

    @JsonProperty("errorMessages")
    private final List<String> errorMessages;
}
