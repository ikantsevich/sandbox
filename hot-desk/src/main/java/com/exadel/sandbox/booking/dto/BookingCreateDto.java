package com.exadel.sandbox.booking.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Data
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class BookingCreateDto extends BookingBaseDto {
    private Long parkingSpotId;
    @NotEmpty(message = "cannot be null")
    private List<LocalDate> dates;
}
