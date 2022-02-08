package com.exadel.sandbox.vacation.controller;

import com.exadel.sandbox.vacation.dto.VacationDto;
import com.exadel.sandbox.vacation.entities.Vacation;
import com.exadel.sandbox.vacation.service.VacationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vacation")
@RequiredArgsConstructor
public class VacationController {
    private final VacationService vacationService;
    private final ModelMapper mapper = new ModelMapper();

    @GetMapping("list")
    List<VacationDto> getTgInfos(){
        return vacationService.toDtoList(vacationService.getVacation(), mapper);
    }

    @GetMapping("{id}")
    VacationDto getTgInfoById(@PathVariable("id") Long id){
        return mapper.map(vacationService.getVacationById(id), VacationDto.class);
    }

    @PostMapping()
    VacationDto createTgInfo(@RequestBody VacationDto vacationDto){
        Vacation vacation = mapper.map(vacationDto, Vacation.class);

        return mapper.map(vacationService.create(vacation), VacationDto.class);
    }

    @DeleteMapping("{id}")
    void deleteTgInfo(@PathVariable("id") Long id){
        vacationService.deleteById(id);
    }

    @PutMapping("{id}")
    VacationDto updateTgInfo(@PathVariable("id") Long id,
                           @RequestBody VacationDto vacationDto){
        Vacation vacation = mapper.map(vacationDto, Vacation.class);

        return mapper.map(vacationService.update(id, vacation), VacationDto.class);
    }
}
