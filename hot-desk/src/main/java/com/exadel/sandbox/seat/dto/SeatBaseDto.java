package com.exadel.sandbox.seat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatBaseDto {
    protected int number;
    protected String status;
    protected String description;
}
