package com.exadel.sandbox.vacation.service;

import com.exadel.sandbox.vacation.dto.VacationCreateDto;
import com.exadel.sandbox.vacation.dto.VacationResponseDto;
import com.exadel.sandbox.vacation.dto.VacationUpdateDto;

import java.util.List;

public interface VacationService {
    List<VacationResponseDto> getVacations();

    VacationResponseDto getVacationById(Long id);

    VacationResponseDto create(VacationCreateDto vacationCreateDto);

    void deleteById(Long id);

    VacationResponseDto update(Long id, VacationUpdateDto vacationUpdateDto);
}
