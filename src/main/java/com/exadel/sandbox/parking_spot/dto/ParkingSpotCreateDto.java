package com.exadel.sandbox.parking_spot.dto;

import com.exadel.sandbox.address.dto.AddressResponseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ParkingSpotCreateDto extends ParkingSpotBaseDto {
    private ParkingSpotResponseDto parkingResponseDto;
}
