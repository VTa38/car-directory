package com.directory.controller;

import com.directory.exception.ExceptionDTO;
import com.directory.exception.HttpStatusException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

public class ExceptionController {

    @ExceptionHandler(value = {HttpStatusException.class})
    public ResponseEntity<Object> handleException(HttpStatusException exception) {

        ExceptionDTO exceptionDTO = new ExceptionDTO(
                exception.getMessage(),
                exception.getStatus(),
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(exceptionDTO, exception.getStatus());
    }
}
