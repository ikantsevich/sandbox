package com.exadel.sandbox.exception.handlers;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.exadel.sandbox.exception.ExceptionResponse;
import com.exadel.sandbox.exception.exceptions.AuthenticationRequestException;
import com.exadel.sandbox.exception.exceptions.DateOutOfBoundException;
import com.exadel.sandbox.exception.exceptions.DoubleBookingInADayException;
import com.exadel.sandbox.exception.exceptions.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

    @ExceptionHandler(value = {AuthenticationRequestException.class})
    public ResponseEntity<ExceptionResponse> handeAuthenticationRequestException(AuthenticationRequestException e){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionResponse response = new ExceptionResponse(
                "bad request",
                status,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(value = {TokenExpiredException.class})
    public ResponseEntity<ExceptionResponse> handeTokenExpiredException(TokenExpiredException e){
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        ExceptionResponse response = new ExceptionResponse(
                e.getMessage(),
                status,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, status);
    }
}
