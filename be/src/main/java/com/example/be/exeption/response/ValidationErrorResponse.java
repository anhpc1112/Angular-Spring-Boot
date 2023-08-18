package com.example.be.exeption.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Data
@Builder
public class ValidationErrorResponse {
    @JsonProperty("result")
    private final String result;
    @JsonProperty("validationErrors")
    private final ValidationErrors validationErrors;

    @Getter
    @Builder
    @EqualsAndHashCode
    public static class ValidationErrors {
        @JsonProperty("name")
        private final String name;
        @JsonProperty("messages")
        private final List<String> messages;
    }
}
