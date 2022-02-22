package com.exadel.sandbox.employee.controller;

import com.exadel.sandbox.employee.dto.employeeDto.EmployeeCreateDto;
import com.exadel.sandbox.employee.dto.employeeDto.EmployeeResponseDto;
import com.exadel.sandbox.employee.dto.employeeDto.EmployeeUpdateDto;
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
    List<EmployeeResponseDto> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("{id}")
    EmployeeResponseDto getEmployeeById(@PathVariable("id") Long id) {

        return employeeService.getEmployeeByID(id);
    }

    @PostMapping()
    EmployeeResponseDto createEmployee(@RequestBody EmployeeCreateDto employeeCreateDto) {

        return employeeService.create(employeeCreateDto);
    }

    @DeleteMapping("{id}")
    void deleteEmployeeById(@PathVariable("id") Long id) {
        employeeService.deleteById(id);
    }

    @PutMapping("{id}")
    EmployeeResponseDto updateEmployee(@PathVariable("id") Long id,
                                       @RequestBody EmployeeUpdateDto employeeUpdateDto) {

        return employeeService.update(id, employeeUpdateDto);
    }

    @PutMapping("{id}/set-tg-info/{tgInfoId}")
    EmployeeResponseDto setTgInfo(@PathVariable Long id,
                                  @PathVariable Long tgInfoId) {
        return employeeService.setTgInfo(id, tgInfoId);
    }

    @PutMapping("{id}/addRole/{roleId}")
    EmployeeResponseDto addRole(@PathVariable Long id,
                                @PathVariable Long roleId) {
        return employeeService.addRole(id, roleId);
    }
}
