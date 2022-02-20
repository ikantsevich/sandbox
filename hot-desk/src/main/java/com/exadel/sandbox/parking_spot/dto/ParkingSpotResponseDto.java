package com.exadel.sandbox.parking_spot.dto;

import com.exadel.sandbox.officeFloor.dto.officeDto.OfficeResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonPropertyOrder({"id",
        "spotNum",
        "status",
        "hasCharger",
        "office"})
public class ParkingSpotResponseDto extends ParkingSpotBaseDto {
    private Long id;
    @JsonProperty("office")
    private OfficeResponseDto officeResponseDto;
    private LocalDateTime created;
    private LocalDateTime modified;
}
