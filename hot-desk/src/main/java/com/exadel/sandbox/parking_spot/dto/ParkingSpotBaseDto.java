package com.exadel.sandbox.parking_spot.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class ParkingSpotBaseDto {
    @NotNull(message = "cannot be null")
    private String status;
    @Min(value = 1L, message = "minimum value is 1")
    private Integer spotNum;
    @NotNull(message = "cannot be null")
    private Boolean hasECharger;
}
