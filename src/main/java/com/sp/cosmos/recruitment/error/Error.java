package com.sp.cosmos.recruitment.error;

import org.springframework.http.HttpStatus;

class Error {
    private HttpStatus status;
    private String message;

    Error(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
