package com.ldv0001.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.nio.file.NoSuchFileException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(TokenIsMissingException.class)
    public ResponseEntity<ApiError> handleTokenException(){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiError error = new ApiError("Authorization token is missing",status);
        return new ResponseEntity<>(error,status);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiError> handleOneElementRequestException(){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiError error = new ApiError("No such Element",status);
        return new ResponseEntity<>(error,status);
    }

    @ExceptionHandler(CannotCreateTransactionException.class)
    public ResponseEntity<ApiError> handleTransactionException(){
        HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;
        ApiError error = new ApiError("Cannot connect to database",status);
        return new ResponseEntity<>(error,status);
    }


    @ExceptionHandler(NoSuchFileException.class)
    public ResponseEntity<ApiError> handleNoFileException(){
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ApiError error = new ApiError("Cannot create/open file",status);
        return new ResponseEntity<>(error,status);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ApiError> handleUserExistException(){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiError error = new ApiError("This user already exist",status);
        return new ResponseEntity<>(error,status);
    }

}
