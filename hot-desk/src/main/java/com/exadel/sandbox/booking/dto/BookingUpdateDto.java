package com.exadel.sandbox.booking.dto;

import com.exadel.sandbox.parking_spot.dto.ParkingSpotResponseDto;
import com.exadel.sandbox.seat.dto.SeatResponseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class BookingUpdateDto extends BookingBaseDto {
    private SeatResponseDto seatResponseDto;
    private ParkingSpotResponseDto parkingSpotResponseDto;
    private LocalDate startDate;
    private LocalDate endDate;
}
