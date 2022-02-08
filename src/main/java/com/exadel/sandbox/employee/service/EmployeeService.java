package com.exadel.sandbox.employee.service;

import com.exadel.sandbox.employee.entity.Employee;
import com.exadel.sandbox.employee.dto.EmployeeDto;
import org.modelmapper.ModelMapper;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployees();

    Employee getEmployeeByID(Long id);

    Employee create(Employee employee);

    void deleteById(Long id);

    Employee update(Long id, Employee employee);

    List<EmployeeDto> toDtoList(List<Employee> employees, ModelMapper mapper);
}
