package com.exadel.sandbox.parking_spot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonPropertyOrder({"officeId", "hasECharger", "spotNum"})
public class ParkingSpotCreateDto extends ParkingSpotBaseDto {
    @NotNull(message = "cannot be null")
    private Long officeId;
}
