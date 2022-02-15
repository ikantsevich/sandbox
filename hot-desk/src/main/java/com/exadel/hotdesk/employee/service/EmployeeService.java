package com.exadel.hotdesk.employee.service;

import com.exadel.hotdesk.employee.dto.employeeDto.EmployeeCreateDto;
import com.exadel.hotdesk.employee.dto.employeeDto.EmployeeResponseDto;
import com.exadel.hotdesk.employee.dto.employeeDto.EmployeeUpdateDto;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponseDto> getEmployees();

    EmployeeResponseDto getEmployeeByID(Long id);

    EmployeeResponseDto create(EmployeeCreateDto employeeCreateDto);

    void deleteById(Long id);

    EmployeeResponseDto update(Long id, EmployeeUpdateDto employeeUpdateDto);
}
