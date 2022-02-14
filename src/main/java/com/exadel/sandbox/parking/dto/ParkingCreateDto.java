package com.exadel.sandbox.parking.dto;

import com.exadel.sandbox.address.dto.AddressResponseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ParkingCreateDto extends ParkingBaseDto {
    private AddressResponseDto addressResponseDto;
}
