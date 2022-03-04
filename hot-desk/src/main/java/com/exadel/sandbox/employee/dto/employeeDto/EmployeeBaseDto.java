package com.exadel.sandbox.employee.dto.employeeDto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class EmployeeBaseDto {
    @NotEmpty(message = "cannot be empty")
    private String firstname;
    @NotEmpty(message = "cannot be empty")
    private String lastname;
    @Email(message = "should be valid")
    private String email;
    @NotEmpty(message = "cannot be empty")
    private String position;
}
