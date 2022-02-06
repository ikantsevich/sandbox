package com.exadel.sandbox.mapper.impl;

import com.exadel.sandbox.dto.VacationDto;
import com.exadel.sandbox.entities.Vacation;
import com.exadel.sandbox.mapper.VacationMapper;

public class VacationMapperImpl implements VacationMapper {
    @Override
    public VacationDto toDto(Vacation vacation) {
        return new VacationDto(
                vacation.getVa_id(),
                vacation.getEmployee(),
                vacation.getVa_start(),
                vacation.getVa_end(),
                vacation.getVa_created(),
                vacation.getVa_modified()
        );
    }

    @Override
    public Vacation toEntity(VacationDto vacationDto) {

        return new Vacation(
                vacationDto.getVa_id(),
                vacationDto.getEmployee(),
                vacationDto.getVa_start(),
                vacationDto.getVa_end(),
                vacationDto.getVa_created(),
                vacationDto.getVa_modified()
        );
    }
}
