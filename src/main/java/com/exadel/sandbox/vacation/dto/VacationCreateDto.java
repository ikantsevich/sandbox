package com.exadel.sandbox.vacation.dto;

import com.exadel.sandbox.employee.dto.employeeDto.EmployeeResponseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class VacationCreateDto extends VacationBaseDto {
    private EmployeeResponseDto employeeResponseDto;
}
