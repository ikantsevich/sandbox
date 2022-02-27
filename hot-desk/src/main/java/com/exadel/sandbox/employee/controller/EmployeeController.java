package com.exadel.sandbox.employee.controller;

import com.exadel.sandbox.employee.service.EmployeeService;
import dtos.employee.dto.employeeDto.EmployeeCreateDto;
import dtos.employee.dto.employeeDto.EmployeeResponseDto;
import dtos.employee.dto.employeeDto.EmployeeUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("list")
    ResponseEntity<List<EmployeeResponseDto>> getEmployees() {
        return employeeService.getList();
    }

    @GetMapping("{id}")
    ResponseEntity<EmployeeResponseDto> getEmployeeById(@PathVariable("id") Long id) {

        return employeeService.getById(id);
    }

    @PostMapping()
    ResponseEntity<EmployeeResponseDto> createEmployee(@RequestBody EmployeeCreateDto employeeCreateDto) {

        return employeeService.create(employeeCreateDto);
    }

    @DeleteMapping("{id}")
    void deleteEmployeeById(@PathVariable("id") Long id) {
        employeeService.delete(id);
    }

    @PutMapping("{id}")
    ResponseEntity<EmployeeResponseDto> updateEmployee(@PathVariable("id") Long id,
                                                       @RequestBody EmployeeUpdateDto employeeUpdateDto) {

        return employeeService.update(id, employeeUpdateDto);
    }

    @PutMapping("{id}/addRole/{roleId}")
    ResponseEntity<EmployeeResponseDto> addRole(@PathVariable Long id,
                                                @PathVariable Long roleId) {
        return employeeService.addRole(id, roleId);
    }
}
