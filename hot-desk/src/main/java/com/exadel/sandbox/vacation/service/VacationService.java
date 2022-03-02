package com.exadel.sandbox.vacation.service;

import com.exadel.sandbox.base.BaseCrudService;
import com.exadel.sandbox.vacation.dto.VacationCreateDto;
import com.exadel.sandbox.vacation.dto.VacationResponseDto;
import com.exadel.sandbox.vacation.dto.VacationUpdateDto;
import com.exadel.sandbox.vacation.entities.Vacation;
import com.exadel.sandbox.vacation.repository.VacationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class VacationService extends BaseCrudService<Vacation, VacationResponseDto, VacationUpdateDto, VacationCreateDto, VacationRepository> {
    @Autowired
    private VacationRepository vacationRepository;

    public VacationService(ModelMapper mapper, VacationRepository repository) {
        super(mapper, repository);
    }


}
