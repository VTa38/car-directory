package com.directory.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public record ExceptionDTO(String massage,
                           HttpStatus status,
                           ZonedDateTime timestamp) {

    public String getMassage() {
        return massage;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }
}