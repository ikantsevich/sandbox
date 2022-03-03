package com.exadel.sandbox.employee.controller;

import com.exadel.sandbox.employee.dto.employeeDto.EmployeeCreateDto;
import com.exadel.sandbox.employee.dto.employeeDto.EmployeeResponseDto;
import com.exadel.sandbox.employee.dto.employeeDto.EmployeeUpdateDto;
import com.exadel.sandbox.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
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

    @GetMapping("/chat-id/{chatId}")
    ResponseEntity<EmployeeResponseDto> getByChatId(@PathVariable String chatId) {
        return employeeService.getByChatId(chatId);
    }

}
