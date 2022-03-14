package com.exadel.sandbox.exception.exceptions;

public class VacationOverlapException extends RuntimeException {
    public VacationOverlapException() {
        super();
    }

    public VacationOverlapException(String message) {
        super(message);
    }

    public VacationOverlapException(String message, Throwable cause) {
        super(message, cause);
    }
}