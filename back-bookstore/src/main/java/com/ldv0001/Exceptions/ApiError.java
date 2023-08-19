package com.ldv0001.Exceptions;

import org.springframework.http.HttpStatus;

public class ApiError {
    private String errorMessage;
    private HttpStatus status;

    public ApiError(String message, HttpStatus status) {
        this.errorMessage = message;
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
