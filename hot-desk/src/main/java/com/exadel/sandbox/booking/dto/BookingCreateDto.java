package com.exadel.sandbox.booking.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class BookingCreateDto extends BookingBaseDto {
    private Long parkingSpotId;
    @NotEmpty(message = "cannot be empty")
    private List<LocalDate> dates;
}
