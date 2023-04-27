package com.learn.sanskar.udemy.cards.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class ErrorResponse {
    private final Integer status;
    private final String error;
    private final String message;
    private String stackTrace;
    private final String path;

    private final LocalDateTime timestamp;

    private List<ValidationErrors> errors;

    @RequiredArgsConstructor
    @Getter
    public static class ValidationErrors {
        private final String field;
        private final String message;
    }
}
