package com.exadel.sandbox.vacation.service;

import com.exadel.sandbox.vacation.dto.VacationDto;

import java.util.List;

public interface VacationService {
    List<VacationDto> getVacations();

    VacationDto getVacationById(Long id);

    VacationDto create(VacationDto vacationDto);

    void deleteById(Long id);

    VacationDto update(Long id, VacationDto vacationDto);
}
