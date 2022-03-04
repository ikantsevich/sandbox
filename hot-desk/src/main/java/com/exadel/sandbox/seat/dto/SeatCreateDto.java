package com.exadel.sandbox.seat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeatCreateDto extends SeatBaseDto {
    @NotNull(message = "cannot be null")
    private Long floorId;
}
