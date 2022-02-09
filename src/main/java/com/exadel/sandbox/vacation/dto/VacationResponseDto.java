package com.exadel.sandbox.vacation.dto;

import com.exadel.sandbox.employee.dto.employeeDto.EmployeeResponseDto;
import lombok.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class VacationResponseDto extends VacationBaseDto{
    private EmployeeResponseDto employeeResponseDto;
    private Long id;
    private LocalDateTime created;
    private LocalDateTime modified;
}
