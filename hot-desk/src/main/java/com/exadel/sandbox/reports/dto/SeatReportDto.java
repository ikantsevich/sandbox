package com.exadel.sandbox.reports.dto;

import lombok.Data;

@Data
public class SeatReportDto {
    private Long id;
    private int seatNum;
    private Long bookedDates;
    private Long freeDates;

    public SeatReportDto(Long id, int seatNum, Long bookedDates) {
        this.id = id;
        this.seatNum = seatNum;
        this.bookedDates = bookedDates;
    }
}
