package com.exadel.sandbox.vacation.dto;

import com.exadel.sandbox.employee.dto.EmployeeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacationDto {
    private Long id;
    private EmployeeDto employeeDto;
    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDateTime created;
    private LocalDateTime modified;
}
