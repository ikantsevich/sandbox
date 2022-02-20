package com.exadel.sandbox.employee.service;

import com.exadel.sandbox.employee.dto.employeeDto.EmployeeCreateDto;
import com.exadel.sandbox.employee.dto.employeeDto.EmployeeResponseDto;
import com.exadel.sandbox.employee.dto.employeeDto.EmployeeUpdateDto;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponseDto> getEmployees();

    EmployeeResponseDto getEmployeeByID(Long id);

    EmployeeResponseDto create(EmployeeCreateDto employeeCreateDto);

    void deleteById(Long id);

    EmployeeResponseDto update(Long id, EmployeeUpdateDto employeeUpdateDto);

    EmployeeResponseDto setTgInfo(Long id, Long tgInfoId);

    EmployeeResponseDto addRole(Long id, Long roleId);
}
