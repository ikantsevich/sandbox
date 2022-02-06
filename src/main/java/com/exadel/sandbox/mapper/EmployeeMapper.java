package com.exadel.sandbox.mapper;

import com.exadel.sandbox.dto.EmployeeDto;
import com.exadel.sandbox.entities.Employee;
import com.exadel.sandbox.entities.TgInfo;

public interface EmployeeMapper {
    Employee toEntity(EmployeeDto employeeDto);

    EmployeeDto toDto(Employee employee);
}
