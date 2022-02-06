package com.exadel.sandbox.mapper.impl;

import com.exadel.sandbox.dto.EmployeeDto;
import com.exadel.sandbox.entities.Employee;
import com.exadel.sandbox.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public Employee toEntity(EmployeeDto employeeDto) {
        return new Employee(
                employeeDto.getEmId(),
                employeeDto.getTgInfo(),
                employeeDto.getEmFirstname(),
                employeeDto.getEmLastname(),
                employeeDto.getEmEmail(),
                employeeDto.getEmPosition(),
                employeeDto.getPreferredSeat(),
                employeeDto.getEmStart(),
                employeeDto.getEmEnd(),
                employeeDto.getEmCreated(),
                employeeDto.getEmModified()
        );
    }

    @Override
    public EmployeeDto toDto(Employee employee) {
        return new EmployeeDto(
                employee.getEmId(),
                employee.getTgInfo(),
                employee.getEmFirstname(),
                employee.getEmLastname(),
                employee.getEmEmail(),
                employee.getEmPosition(),
                employee.getPreferredSeat(),
                employee.getEmStart(),
                employee.getEmEnd(),
                employee.getEmCreated(),
                employee.getEmModified()
        );
    }
}
