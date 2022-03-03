package com.exadel.sandbox.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.sql.SQLException;
import java.time.LocalDateTime;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<ExceptionResponse> handleEntityNotFoundException(EntityNotFoundException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ExceptionResponse response = new ExceptionResponse(
                e.getMessage(),
                status,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(value = {CannotBeNullException.class})
    public ResponseEntity<ExceptionResponse> handleCannotBeNullException(CannotBeNullException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ExceptionResponse response = new ExceptionResponse(
                e.getMessage(),
                status,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(value = {DoubleBookingInADayException.class})
    public ResponseEntity<ExceptionResponse> handleDoubleBookingInADayException(DoubleBookingInADayException e){
        HttpStatus status = HttpStatus.CONFLICT;

        ExceptionResponse response = new ExceptionResponse(
                e.getMessage(),
                status,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(value = {DateOutOfBoundException.class})
    public ResponseEntity<ExceptionResponse> handleOutOfBoundException(DateOutOfBoundException e){
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ExceptionResponse response = new ExceptionResponse(
                e.getMessage(),
                status,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(value = {EmptyResultDataAccessException.class})
    public ResponseEntity<ExceptionResponse> handleEmptyResultDateAccessException(EmptyResultDataAccessException e){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ExceptionResponse response = new ExceptionResponse(
                "entity doesn't exist",
                status,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(value = {SQLException.class})
    public ResponseEntity<ExceptionResponse> handleSQLException(SQLException e){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponse response = new ExceptionResponse(
                e.getMessage(),
                status,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, status);
    }
}
