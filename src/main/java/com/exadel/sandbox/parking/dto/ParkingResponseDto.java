package com.exadel.sandbox.parking.dto;

import com.exadel.sandbox.address.dto.AddressResponseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class ParkingResponseDto extends ParkingBaseDto {

    private Long id;
    private AddressResponseDto addressResponseDto;
    private LocalDateTime created;
    private LocalDateTime modified;

}
