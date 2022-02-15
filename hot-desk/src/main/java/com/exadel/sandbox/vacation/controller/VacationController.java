package com.exadel.sandbox.vacation.controller;

import com.exadel.sandbox.vacation.dto.VacationCreateDto;
import com.exadel.sandbox.vacation.dto.VacationResponseDto;
import com.exadel.sandbox.vacation.service.VacationService;
import com.exadel.sandbox.vacation.dto.VacationUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vacation")
@RequiredArgsConstructor
public class VacationController {
    private final VacationService vacationService;

    @GetMapping("list")
    List<VacationResponseDto> getTgInfos() {
        return vacationService.getVacations();
    }

    @GetMapping("{id}")
    VacationResponseDto getTgInfoById(@PathVariable("id") Long id) {
        return vacationService.getVacationById(id);
    }

    @PostMapping()
    VacationResponseDto createTgInfo(@RequestBody VacationCreateDto vacationCreateDto) {
        return vacationService.create(vacationCreateDto);
    }

    @DeleteMapping("{id}")
    void deleteTgInfo(@PathVariable("id") Long id) {
        vacationService.deleteById(id);
    }

    @PutMapping("{id}")
    VacationResponseDto updateTgInfo(@PathVariable("id") Long id,
                                     @RequestBody VacationUpdateDto vacationUpdateDto) {

        return vacationService.update(id, vacationUpdateDto);
    }
}
