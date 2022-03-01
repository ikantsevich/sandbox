package com.exadel.sandbox.exception;

public class CannotBeNullException extends RuntimeException {
    public CannotBeNullException() {
        super();
    }

    public CannotBeNullException(String message) {
        super(message);
    }

    public CannotBeNullException(String message, Throwable cause) {
        super(message, cause);
    }
}