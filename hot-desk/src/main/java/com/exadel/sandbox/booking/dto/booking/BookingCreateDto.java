package com.exadel.sandbox.booking.dto.booking;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class BookingCreateDto extends BookingBaseDto {
    private Long parkingSpotId;
    private List<Date> dates;
}
