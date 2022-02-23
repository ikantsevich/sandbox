package com.exadel.sandbox.booking.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class DateOutOfBoundResponse {
    private final String message;
    private final HttpStatus status;
    private final LocalDateTime localDateTime;
}