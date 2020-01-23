package com.sp.cosmos.recruitment.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@SuppressWarnings("unused")
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler({DatePeriodException.class, ReservationException.class})
    protected ResponseEntity<Object> handleSocketTimeoutException(
            RuntimeException ex) {
        Error apiError = new Error(BAD_REQUEST, ex.getMessage());
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(com.sp.cosmos.recruitment.error.Error apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}