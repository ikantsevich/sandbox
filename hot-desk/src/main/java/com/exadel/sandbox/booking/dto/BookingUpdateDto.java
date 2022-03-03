package com.exadel.sandbox.booking.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class BookingUpdateDto extends BookingBaseDto {
    private Long parkingSpotId;
    private List<LocalDate> dates;
}
