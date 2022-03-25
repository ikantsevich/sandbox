package com.exadel.sandbox.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class BookingUpdateDto extends BookingBaseDto {
    private Long parkingSpotId;
    @NotEmpty(message = "cannot be null")
    private List<LocalDate> dates;
}
