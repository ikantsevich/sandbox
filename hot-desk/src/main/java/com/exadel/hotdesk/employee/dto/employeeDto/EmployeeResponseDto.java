package com.exadel.hotdesk.employee.dto.employeeDto;

import com.exadel.hotdesk.employee.dto.tgInfoDto.TgInfoResponseDto;
import lombok.*;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class EmployeeResponseDto extends EmployeeBaseDto {
    private Long id;
    private TgInfoResponseDto tgInfoResponseDto;
    private Integer preferredSeat;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime employmentStart;
    private LocalDateTime employmentEnd;
}
