package com.exadel.sandbox.exception.exceptions;

public class AuthenticationRequestException extends RuntimeException {
    public AuthenticationRequestException() {
        super();
    }

    public AuthenticationRequestException(String message) {
        super(message);
    }

    public AuthenticationRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}