package com.exadel.sandbox.controller;

import com.exadel.sandbox.dto.VacationDto;
import com.exadel.sandbox.entities.Vacation;
import com.exadel.sandbox.mapper.VacationMapper;
import com.exadel.sandbox.service.VacationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("vacation")
@RequiredArgsConstructor
public class VacationController {
    private final VacationService vacationService;
    private final VacationMapper mapper;

    @GetMapping("list")
    List<VacationDto> getTgInfos(){
        List<Vacation> vacations = vacationService.getVacation();
        return vacations.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    VacationDto getTgInfoById(@PathVariable("id") int id){
        Vacation vacation = vacationService.getVacationById(id);
        return mapper.toDto(vacation);
    }

    @PutMapping()
    VacationDto createTgInfo(@RequestBody VacationDto vacationDto){
        Vacation vacation = mapper.toEntity(vacationDto);
        vacation = vacationService.create(vacation);
        return mapper.toDto(vacation);
    }

    @DeleteMapping("{id}")
    void deleteTgInfo(@PathVariable("id") int id){
        vacationService.deleteById(id);
    }

    @PostMapping("{id}")
    VacationDto updateTgInfo(@PathVariable("id") int id,
                           @RequestBody VacationDto vacationDto){
        Vacation vacation = mapper.toEntity(vacationDto);
        vacation = vacationService.update(id, vacation);
        return mapper.toDto(vacation);
    }
}
