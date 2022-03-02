package com.exadel.sandbox.exception;

public class DateOutOfBoundException extends RuntimeException {
    public DateOutOfBoundException() {
        super();
    }

    public DateOutOfBoundException(String message) {
        super(message);
    }

    public DateOutOfBoundException(String message, Throwable cause) {
        super(message, cause);
    }
}