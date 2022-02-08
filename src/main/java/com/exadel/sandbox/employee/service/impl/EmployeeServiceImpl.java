package com.exadel.sandbox.employee.service.impl;

import com.exadel.sandbox.employee.service.EmployeeService;
import com.exadel.sandbox.exception.EntityNotFoundException;
import com.exadel.sandbox.employee.entity.Employee;
import com.exadel.sandbox.employee.dto.EmployeeDto;
import com.exadel.sandbox.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public List<EmployeeDto> getEmployees() {
        List<Employee> employees = employeeRepository.findAll();


        return employees.stream().map(employee -> mapper.map(employee, EmployeeDto.class)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto getEmployeeByID(Long id) {
        Optional<Employee> byId = employeeRepository.findById(id);

        Employee employee = byId.orElseThrow(() -> new EntityNotFoundException("Employee with id: " + id + " not found"));

        return mapper.map(employee, EmployeeDto.class);
    }

    @Override
    public EmployeeDto create(EmployeeDto employeeDto) {
        Employee employee = mapper.map(employeeDto, Employee.class);

        return mapper.map(employeeRepository.save(employee), EmployeeDto.class);
    }

    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public EmployeeDto update(Long id, EmployeeDto employeeDto) {
        Employee employee = mapper.map(employeeDto, Employee.class);
        employee.setId(id);
        return mapper.map(employeeRepository.save(employee), EmployeeDto.class);
    }
}
