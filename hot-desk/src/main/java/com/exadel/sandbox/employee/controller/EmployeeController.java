package com.exadel.sandbox.employee.controller;

import com.exadel.sandbox.employee.dto.employeeDto.EmployeeCreateDto;
import com.exadel.sandbox.employee.dto.employeeDto.EmployeeResponseDto;
import com.exadel.sandbox.employee.dto.employeeDto.EmployeeUpdateDto;
import com.exadel.sandbox.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    @GetMapping("list")
    ResponseEntity<List<EmployeeResponseDto>> getEmployees() {
        return employeeService.getList();
    }

    @GetMapping("{id}")
    ResponseEntity<EmployeeResponseDto> getEmployeeById(@PathVariable("id") Long id, Principal principal) {

        return employeeService.getById(id, principal);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    @PostMapping()
    ResponseEntity<EmployeeResponseDto> createEmployee(@Valid @RequestBody EmployeeCreateDto employeeCreateDto) {

        return employeeService.create(employeeCreateDto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    @DeleteMapping("{id}")
    void deleteEmployeeById(@PathVariable("id") Long id) {
        employeeService.delete(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    @PutMapping("{id}")
    ResponseEntity<EmployeeResponseDto> updateEmployee(@PathVariable("id") Long id,
                                                       @Valid @RequestBody EmployeeUpdateDto employeeUpdateDto) {

        return employeeService.update(id, employeeUpdateDto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    @PutMapping("{id}/addRole/{roleId}")
    ResponseEntity<EmployeeResponseDto> addRole(@PathVariable Long id,
                                                @PathVariable Long roleId) {
        return employeeService.addRole(id, roleId);
    }

    @GetMapping("/chat-id/{chatId}")
    ResponseEntity<EmployeeResponseDto> getByChatId(@PathVariable String chatId) {
        return employeeService.getByChatId(chatId);
    }

    @GetMapping("{id}/booked-dates")
    ResponseEntity<List<LocalDate>> getEmployeesBookedDates(@PathVariable Long id, Principal principal) {
        return employeeService.getEmployeeBookedDates(id, principal);
    }

    @GetMapping("{id}/booked-dates/list")
    ResponseEntity<List<LocalDate>> getEmployeesBookedDatesAll(@PathVariable Long id, Principal principal) {
        return employeeService.getEmployeeBookedDatesAll(id, principal);
    }
}
