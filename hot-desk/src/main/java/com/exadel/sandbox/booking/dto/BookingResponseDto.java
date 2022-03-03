package com.exadel.sandbox.booking.dto;

import com.exadel.sandbox.officeFloor.dto.officeDto.OfficeResponseDto;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonPropertyOrder({"id", "employeeId", "seatId", "parkingSpotId", "dates"})
public class BookingResponseDto extends BookingBaseDto {
    private Long id;
    private Long employeeId;
    private Long seatId;
    private Long parkingSpotId;
    private List<LocalDate> dates;
    private OfficeResponseDto office;
    private Integer floorNumber;
    private LocalDateTime created;
    private LocalDateTime modified;
}
