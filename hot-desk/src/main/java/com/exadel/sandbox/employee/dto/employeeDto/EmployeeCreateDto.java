package com.exadel.sandbox.employee.dto.employeeDto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonPropertyOrder({"firstname", "lastname"})
public class EmployeeCreateDto extends EmployeeBaseDto {
    private LocalDateTime employmentStart;
    private LocalDateTime employmentEnd;
}
