package com.exadel.hotdesk.vacation.dto;

import com.exadel.hotdesk.employee.dto.employeeDto.EmployeeResponseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class VacationCreateDto extends VacationBaseDto {
    private EmployeeResponseDto employeeResponseDto;
}
