package com.exadel.sandbox.mapper;

import com.exadel.sandbox.dto.VacationDto;
import com.exadel.sandbox.entities.Vacation;

public interface VacationMapper {
    VacationDto toDto(Vacation vacation);

    Vacation toEntity(VacationDto vacationDto);
}
