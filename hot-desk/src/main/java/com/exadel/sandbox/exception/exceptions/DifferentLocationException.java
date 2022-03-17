package com.exadel.sandbox.exception.exceptions;

public class DifferentLocationException extends RuntimeException {
    public DifferentLocationException() {
        super();
    }

    public DifferentLocationException(String message) {
        super(message);
    }

    public DifferentLocationException(String message, Throwable cause) {
        super(message, cause);
    }
}

