package com.example.be.exeption;

import com.example.be.constant.MessagePropertiesConstants;
import com.example.be.exeption.response.BusinessErrorResponse;
import com.example.be.exeption.response.ValidationErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String RESULT_NG = "NG";

    private final MessageSource messageSource;

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationException(
            ValidationException error) {
        log.error(error.getMessage(), Objects.isNull(error.getCause()) ? error : error.getCause());

        ValidationErrorResponse.ValidationErrors validationErrors = ValidationErrorResponse.ValidationErrors.builder()
                .messages(error.getErrorMessages()).build();

        ValidationErrorResponse validationErrorResponse = ValidationErrorResponse.builder()
                .validationErrors(validationErrors)
                .result(RESULT_NG).build();

        return ResponseEntity.status(BAD_REQUEST).body(validationErrorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleRuntimeException(Exception exception, WebRequest request) {
        log.error(messageSource.getMessage("log.error.catch.Exception", null,
                Locale.getDefault()), exception);

        BusinessErrorResponse response =
                BusinessErrorResponse.builder()
                        .result(RESULT_NG)
                        .errorMessages(
                                Arrays.asList(
                                        messageSource.getMessage(MessagePropertiesConstants.RESPONSE_500,
                                                null, Locale.getDefault())))
                        .build();
        return super.handleExceptionInternal(
                exception,
                response,
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                request
        );
    }
}
