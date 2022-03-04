package com.exadel.sandbox.booking.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BookingBaseDto {
    @NotNull(message = "cannot be null")
    private Long employeeId;
    @NotNull(message = "cannot be null")
    private Long seatId;
}
