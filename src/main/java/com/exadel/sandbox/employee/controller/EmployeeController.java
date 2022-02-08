package com.exadel.sandbox.employee.controller;

import com.exadel.sandbox.employee.dto.EmployeeDto;
import com.exadel.sandbox.employee.entity.Employee;
import com.exadel.sandbox.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ModelMapper mapper = new ModelMapper();

    @GetMapping("list")
    List<EmployeeDto> getEmployees() {
        return employeeService.toDtoList(employeeService.getEmployees(), mapper);
    }

    @GetMapping("{id}")
    EmployeeDto getEmployeeById(@PathVariable("id") Long id) {
        Employee employee = employeeService.getEmployeeByID(id);

        return mapper.map(employee, EmployeeDto.class);
    }

    @PostMapping()
    EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee employee = mapper.map(employeeDto, Employee.class);

        return mapper.map(employeeService.create(employee), EmployeeDto.class);
    }

    @DeleteMapping("{id}")
    void deleteEmployeeById(@PathVariable("id") Long id) {
        employeeService.deleteById(id);
    }

    @PutMapping("{id}")
    EmployeeDto updateEmployee(@PathVariable("id") Long id,
                               @RequestBody EmployeeDto employeeDto) {

        Employee employee = mapper.map(employeeDto, Employee.class);

        return mapper.map(employeeService.update(id, employee), EmployeeDto.class);
    }
}
