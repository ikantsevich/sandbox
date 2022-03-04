package com.exadel.sandbox.seat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatBaseDto {
    @Min(value = 1L, message = "minimum value is 1")
    protected int number;
    @NotNull(message = "cannot be null")
    protected String status;
    @Size(min = 4, max = 200, message = "should be 4 character at least and 200 characters at most")
    protected String description;
}
