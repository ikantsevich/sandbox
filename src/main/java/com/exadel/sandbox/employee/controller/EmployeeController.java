package com.exadel.sandbox.employee.controller;

import com.exadel.sandbox.employee.dto.EmployeeDto;
import com.exadel.sandbox.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("list")
    List<EmployeeDto> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("{id}")
    EmployeeDto getEmployeeById(@PathVariable("id") Long id) {

        return employeeService.getEmployeeByID(id);
    }

    @PostMapping()
    EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto) {

        return employeeService.create(employeeDto);
    }

    @DeleteMapping("{id}")
    void deleteEmployeeById(@PathVariable("id") Long id) {
        employeeService.deleteById(id);
    }

    @PutMapping("{id}")
    EmployeeDto updateEmployee(@PathVariable("id") Long id,
                               @RequestBody EmployeeDto employeeDto) {


        return employeeService.update(id, employeeDto);
    }
}
