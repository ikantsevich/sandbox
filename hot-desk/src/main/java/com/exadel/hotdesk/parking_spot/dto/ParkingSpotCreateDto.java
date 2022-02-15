package com.exadel.hotdesk.parking_spot.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ParkingSpotCreateDto extends ParkingSpotBaseDto {
    private ParkingSpotResponseDto parkingResponseDto;
}
