package com.directory.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends HttpStatusException {

    public BadRequestException(String message, HttpStatus status) {
        super(message, status);
    }
}
