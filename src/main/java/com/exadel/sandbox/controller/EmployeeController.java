package com.exadel.sandbox.controller;

import com.exadel.sandbox.dto.EmployeeDto;
import com.exadel.sandbox.entities.Employee;
import com.exadel.sandbox.mapper.EmployeeMapper;
import com.exadel.sandbox.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeMapper mapper;

    @GetMapping("list")
    List<EmployeeDto> getEmployees() {
        List<Employee> employees = employeeService.getEmployees();
        return employees.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    EmployeeDto getEmployeeById(@PathVariable("id") Integer id) {
        Employee employee = employeeService.getEmployeeByID(id);
        System.out.println(employee.getEmId());
        return mapper.toDto(employee);
    }

    @PutMapping()
    EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee employee = mapper.toEntity(employeeDto);
        employee = employeeService.create(employee);
        return mapper.toDto(employee);
    }

    @DeleteMapping("{id}")
    void deleteEmployeeById(@PathVariable("id") int id) {
        employeeService.deleteById(id);
    }

    @PostMapping("{id}")
    EmployeeDto updateEmployee(@PathVariable("id") int id,
                               @RequestBody EmployeeDto employeeDto) {
        Employee employee = mapper.toEntity(employeeDto);
        employee = employeeService.update(id, employee);
        return mapper.toDto(employee);
    }
}
