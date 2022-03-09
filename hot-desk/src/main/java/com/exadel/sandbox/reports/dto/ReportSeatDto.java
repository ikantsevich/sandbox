package com.exadel.sandbox.reports.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportSeatDto {
    private Long id;
    private int number;
    private String status;
}
