package com.exadel.hotdesk.employee.dto.employeeDto;

import com.exadel.hotdesk.employee.dto.tgInfoDto.TgInfoResponseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class EmployeeCreateDto extends EmployeeBaseDto {
    private TgInfoResponseDto tgInfoResponseDto;
    private LocalDateTime employmentStart;
    private LocalDateTime employmentEnd;
}
