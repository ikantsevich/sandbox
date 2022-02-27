package com.exadel.sandbox.vacation.service;

import com.exadel.sandbox.base.BaseCrudService;
import com.exadel.sandbox.vacation.entities.Vacation;
import com.exadel.sandbox.vacation.repository.VacationRepository;
import dtos.vacation.dto.VacationCreateDto;
import dtos.vacation.dto.VacationResponseDto;
import dtos.vacation.dto.VacationUpdateDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class VacationService extends BaseCrudService<Vacation, VacationResponseDto, VacationUpdateDto, VacationCreateDto, VacationRepository> {
    public VacationService(ModelMapper mapper, VacationRepository repository) {
        super(mapper, repository);
    }
}
