package com.exadel.sandbox.seat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SeatUpdateDto extends SeatBaseDto {
    @NotNull(message = "cannot be null")
    private Long floorId;
}
