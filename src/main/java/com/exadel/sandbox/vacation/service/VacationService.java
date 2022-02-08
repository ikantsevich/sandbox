package com.exadel.sandbox.vacation.service;

import com.exadel.sandbox.vacation.dto.VacationDto;
import com.exadel.sandbox.vacation.entities.Vacation;
import org.modelmapper.ModelMapper;

import java.util.List;

public interface VacationService {
    List<Vacation> getVacation();

    Vacation getVacationById(Long id);

    Vacation create(Vacation vacation);

    void deleteById(Long id);

    Vacation update(Long id, Vacation vacation);

    List<VacationDto> toDtoList(List<Vacation> vacations, ModelMapper mapper);
}
