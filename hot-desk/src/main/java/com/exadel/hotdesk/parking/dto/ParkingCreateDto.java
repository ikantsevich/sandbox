package com.exadel.hotdesk.parking.dto;

import com.exadel.hotdesk.address.dto.AddressResponseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ParkingCreateDto extends ParkingBaseDto {
    private AddressResponseDto addressResponseDto;
}
