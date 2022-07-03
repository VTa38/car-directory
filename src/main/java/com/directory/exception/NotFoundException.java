package com.directory.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends HttpStatusException {

    public NotFoundException(String message, HttpStatus status) {
        super(message, status);
    }
}
