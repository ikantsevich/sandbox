package com.exadel.sandbox.employee.service;

import com.exadel.sandbox.employee.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> getEmployees();

    EmployeeDto getEmployeeByID(Long id);

    EmployeeDto create(EmployeeDto employeeDto);

    void deleteById(Long id);

    EmployeeDto update(Long id, EmployeeDto employeeDto);
}
