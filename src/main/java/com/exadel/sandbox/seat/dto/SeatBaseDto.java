package com.exadel.sandbox.seat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatBaseDto {
    private int number;
    private String status;
    private String description;
}
