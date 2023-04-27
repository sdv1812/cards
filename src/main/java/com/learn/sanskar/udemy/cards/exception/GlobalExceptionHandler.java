package com.learn.sanskar.udemy.cards.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Value("${exception.trace.capture}")
    private Boolean isStackTraceEnabled;

    private final HttpServletRequest httpServletRequest;

    @Autowired
    public GlobalExceptionHandler(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorResponse errorResponse = createErrorResponse(ex,
                        "Validation errors, please check 'errors' field for more details",
                        HttpStatus.BAD_REQUEST);
        List<ErrorResponse.ValidationErrors> errors = ex.getFieldErrors()
                .stream()
                .map(fieldError -> new ErrorResponse.ValidationErrors(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();
        errorResponse.setErrors(errors);
        if (isStackTraceEnabled) errorResponse.setStackTrace(ExceptionUtils.getStackTrace(ex));
        return createErrorResponseEntity(errorResponse);
    }

    private ResponseEntity<Object> createErrorResponseEntity(ErrorResponse errorResponse) {
        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }

    private ErrorResponse createErrorResponse(Exception ex, String message, HttpStatus httpStatus) {
        LocalDateTime timestamp = LocalDateTime.now();
        return new ErrorResponse(
                httpStatus.value(),
                httpStatus.getReasonPhrase(),
                message,
                httpServletRequest.getRequestURI(),
                timestamp
        );
    }
}
