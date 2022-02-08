package com.exadel.sandbox.employee.service.impl;

import com.exadel.sandbox.employee.service.EmployeeService;
import com.exadel.sandbox.exception.EntityNotFoundException;
import com.exadel.sandbox.employee.entity.Employee;
import com.exadel.sandbox.employee.dto.EmployeeDto;
import com.exadel.sandbox.employee.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeByID(Long id) {
        Optional<Employee> byId = employeeRepository.findById(id);

        return byId.orElseThrow(() -> new EntityNotFoundException("Employee with id: " + id + " not found"));
    }

    @Override
    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee update(Long id, Employee employee) {
        employee.setId(id);
        return employeeRepository.save(employee);
    }

    @Override
    public List<EmployeeDto> toDtoList(List<Employee> employees, ModelMapper mapper) {
        return employees.stream().map(employee -> mapper.map(employee, EmployeeDto.class)).collect(Collectors.toList());
    }
}
