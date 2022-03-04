package com.exadel.sandbox.exception.exceptions;

public class DoubleBookingInADayException extends RuntimeException {
    public DoubleBookingInADayException() {
        super();
    }

    public DoubleBookingInADayException(String message) {
        super(message);
    }

    public DoubleBookingInADayException(String message, Throwable cause) {
        super(message, cause);
    }
}
