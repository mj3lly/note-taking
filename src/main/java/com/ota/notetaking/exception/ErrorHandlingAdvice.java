package com.ota.notetaking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandlingAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ErrorResponse handleResourceNotFoundException(ResourceNotFoundException exception) {
        return ErrorResponse.builder()
                .errorMessage(exception.getMessage())
                .responseCode(HttpStatus.NOT_FOUND.value())
            .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ExistingResourceException.class)
    public ErrorResponse handleExistingResourceException(ExistingResourceException exception) {
        return ErrorResponse.builder()
                .errorMessage(exception.getMessage())
                .responseCode(HttpStatus.BAD_REQUEST.value())
            .build();
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BodyRequestException.class)
    public ErrorResponse handleBodyRequestException(BodyRequestException exception) {
        return ErrorResponse.builder()
                .errorMessage(exception.getMessage())
                .responseCode(HttpStatus.BAD_REQUEST.value())
            .build();
    }

}
