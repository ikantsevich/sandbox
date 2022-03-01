package com.exadel.sandbox.booking.dto.booking;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class BookingUpdateDto extends BookingBaseDto {
    private Long seatId;
    private Long parkingId;
    private List<Date> datesUpdateDtoList;
}
