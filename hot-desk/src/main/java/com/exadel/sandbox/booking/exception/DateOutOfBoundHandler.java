package com.exadel.sandbox.booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class DateOutOfBoundHandler {
    @ExceptionHandler(value = {DateOutOfBoundException.class})
    public ResponseEntity<Object> handleDateOutOfBoundException(DateOutOfBoundException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        DateOutOfBoundResponse response = new DateOutOfBoundResponse(
                e.getMessage(),
                status,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, status);
    }
}