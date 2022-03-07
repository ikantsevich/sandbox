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
    ResponseEntity<List<VacationResponseDto>> getTgInfos() {
        return vacationService.getList();
    }

    @GetMapping("{id}")
    ResponseEntity<VacationResponseDto> getTgInfoById(@PathVariable("id") Long id) {
        return vacationService.getById(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    @PostMapping()
    ResponseEntity<VacationResponseDto> createTgInfo(@Valid @RequestBody VacationCreateDto vacationCreateDto) {
        return vacationService.create(vacationCreateDto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    @DeleteMapping("{id}")
    void deleteTgInfo(@PathVariable("id") Long id) {
        vacationService.delete(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    @PutMapping("{id}")
    ResponseEntity<VacationResponseDto> updateTgInfo(@PathVariable("id") Long id,
                                                     @Valid @RequestBody VacationUpdateDto vacationUpdateDto) {

        return vacationService.update(id, vacationUpdateDto);
    }
}
