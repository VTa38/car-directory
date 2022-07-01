package com.directory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

public class ApiExceptionHandler {

    @ExceptionHandler(value = {RequestException.class})
    public ResponseEntity<Object> handleRequestException(RequestException exception) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ApiException apiException = new ApiException(exception.getMessage(),
                exception,
                badRequest,
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(NotFoundException exception) {
        HttpStatus NotFoundRequest = HttpStatus.NOT_FOUND;

        ApiException apiException = new ApiException(exception.getMessage(),
                exception,
                NotFoundRequest,
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(apiException, NotFoundRequest);
    }
}
