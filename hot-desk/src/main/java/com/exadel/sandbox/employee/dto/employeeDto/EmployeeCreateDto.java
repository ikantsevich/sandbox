package com.exadel.sandbox.employee.dto.employeeDto;

import com.exadel.sandbox.employee.dto.tgInfoDto.TgInfoCreateDto;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonPropertyOrder({"firstname", "lastname"})
public class EmployeeCreateDto extends EmployeeBaseDto {
    @NotEmpty(message = "cannot be empty")
    private List<String> roles;
    private TgInfoCreateDto tgInfoCreateDto;
    @NotNull(message = "cannot be null")
    private LocalDateTime employmentStart;
    private LocalDateTime employmentEnd;
}
