package com.exadel.sandbox.booking.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class BookingCreateDto extends BookingBaseDto {
    private Long parkingSpotId;
    @NotEmpty(message = "cannot be null")
    private List<LocalDate> dates;
}
