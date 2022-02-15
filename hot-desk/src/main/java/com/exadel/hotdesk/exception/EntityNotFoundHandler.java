package com.exadel.hotdesk.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class EntityNotFoundHandler {

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        EntityNotFoundResponse response = new EntityNotFoundResponse(
                e.getMessage(),
                status,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, status);
    }
}
