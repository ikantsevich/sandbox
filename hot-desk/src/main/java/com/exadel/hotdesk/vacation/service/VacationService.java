package com.exadel.hotdesk.vacation.service;

import com.exadel.hotdesk.vacation.dto.VacationCreateDto;
import com.exadel.hotdesk.vacation.dto.VacationResponseDto;
import com.exadel.hotdesk.vacation.dto.VacationUpdateDto;

import java.util.List;

public interface VacationService {
    List<VacationResponseDto> getVacations();

    VacationResponseDto getVacationById(Long id);

    VacationResponseDto create(VacationCreateDto vacationCreateDto);

    void deleteById(Long id);

    VacationResponseDto update(Long id, VacationUpdateDto vacationUpdateDto);
}
