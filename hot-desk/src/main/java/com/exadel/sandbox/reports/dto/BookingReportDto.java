package com.exadel.sandbox.reports.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingReportDto {
    private Long id;
    private SeatReportDto seatReportDto;
    private LocalDate date;
}
