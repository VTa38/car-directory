package com.directory.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public record ApiException(String massage, Throwable throwable,
                           HttpStatus status, ZonedDateTime date) {

    public String getMassage() {
        return massage;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public ZonedDateTime getDate() {
        return date;
    }
}