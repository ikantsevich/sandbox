package com.exadel.sandbox.booking.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class BookingResponseDto extends BookingBaseDto {
    private Long id;
    private Long employeeId;
    private Long seatId;
    private Long parkingSpotId;
    private List<Date> dates;
    private LocalDateTime created;
    private LocalDateTime modified;
}
