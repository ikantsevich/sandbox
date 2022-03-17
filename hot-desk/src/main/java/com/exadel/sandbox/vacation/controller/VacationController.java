package com.exadel.sandbox.vacation.controller;

import com.exadel.sandbox.vacation.dto.VacationCreateDto;
import com.exadel.sandbox.vacation.dto.VacationResponseDto;
import com.exadel.sandbox.vacation.dto.VacationUpdateDto;
import com.exadel.sandbox.vacation.service.VacationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("vacation")
@RequiredArgsConstructor
public class VacationController {
    private final VacationService vacationService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    @GetMapping("list")
    ResponseEntity<List<VacationResponseDto>> getVacations() {
        return vacationService.getList();
    }

    @GetMapping("{id}")
    ResponseEntity<VacationResponseDto> getVacationById(@PathVariable("id") Long id) {
        return vacationService.getById(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    @PostMapping()
    ResponseEntity<VacationResponseDto> createVacation(@Valid @RequestBody VacationCreateDto vacationCreateDto) {
        return vacationService.create(vacationCreateDto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    @DeleteMapping("{id}")
    void deleteVacation(@PathVariable("id") Long id) {
        vacationService.delete(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    @PutMapping("{id}")
    ResponseEntity<VacationResponseDto> updateVacation(@PathVariable("id") Long id,
                                                     @Valid @RequestBody VacationUpdateDto vacationUpdateDto) {

        return vacationService.update(id, vacationUpdateDto);
    }

    @GetMapping("employee/{employeeId}")
    ResponseEntity<List<VacationResponseDto>> getByEmployeeID(@PathVariable Long employeeId){
        return vacationService.getByEmployeeId(employeeId);
    }
}
