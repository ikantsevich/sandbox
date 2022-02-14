package com.exadel.sandbox.booking.dto;

import com.exadel.sandbox.address.dto.AddressResponseDto;
import com.exadel.sandbox.parking_spot.dto.ParkingSpotResponseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class BookingResponseDto extends BookingBaseDto {

    private Long id;
    private ParkingSpotResponseDto parkingSpotResponseDto;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime created;
    private LocalDateTime modified;

}
