package com.exadel.sandbox.vacation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacationBaseDto {
    @NotNull(message = "cannot be null")
    private LocalDateTime start;
    @NotNull(message = "cannot be null")
    private LocalDateTime end;
}
