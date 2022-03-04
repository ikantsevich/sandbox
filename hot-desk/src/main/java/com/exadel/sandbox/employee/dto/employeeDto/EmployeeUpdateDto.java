package com.exadel.sandbox.employee.dto.employeeDto;

import com.exadel.sandbox.employee.dto.tgInfoDto.TgInfoUpdateDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class EmployeeUpdateDto extends EmployeeBaseDto {
    @NotNull(message = "cannot be null")
    private LocalDateTime employmentStart;
    private LocalDateTime employmentEnd;
}
