package com.exadel.sandbox.reports.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookedSeatDto {
    private Long id;
    private ReportSeatDto reportSeatDto;
    private List<LocalDate> dates;
}
