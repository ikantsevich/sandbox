package com.exadel.hotdesk.vacation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacationBaseDto {
    private LocalDateTime start;
    private LocalDateTime end;
}
