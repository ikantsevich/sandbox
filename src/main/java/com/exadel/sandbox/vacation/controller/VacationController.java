package com.exadel.sandbox.vacation.controller;

import com.exadel.sandbox.vacation.dto.VacationDto;
import com.exadel.sandbox.vacation.service.VacationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vacation")
@RequiredArgsConstructor
public class VacationController {
    private final VacationService vacationService;

    @GetMapping("list")
    List<VacationDto> getTgInfos(){
        return vacationService.getVacations();
    }

    @GetMapping("{id}")
    VacationDto getTgInfoById(@PathVariable("id") Long id){
        return vacationService.getVacationById(id);
    }

    @PostMapping()
    VacationDto createTgInfo(@RequestBody VacationDto vacationDto){
        return vacationService.create(vacationDto);
    }

    @DeleteMapping("{id}")
    void deleteTgInfo(@PathVariable("id") Long id){
        vacationService.deleteById(id);
    }

    @PutMapping("{id}")
    VacationDto updateTgInfo(@PathVariable("id") Long id,
                           @RequestBody VacationDto vacationDto){

        return vacationService.update(id, vacationDto);
    }
}
