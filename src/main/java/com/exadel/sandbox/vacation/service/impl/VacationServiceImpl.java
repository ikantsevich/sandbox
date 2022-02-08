package com.exadel.sandbox.vacation.service.impl;

import com.exadel.sandbox.exception.EntityNotFoundException;
import com.exadel.sandbox.vacation.dto.VacationDto;
import com.exadel.sandbox.vacation.entities.Vacation;
import com.exadel.sandbox.vacation.repository.VacationRepository;
import com.exadel.sandbox.vacation.service.VacationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class VacationServiceImpl implements VacationService {

    private final VacationRepository vacationRepository;
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public List<VacationDto> getVacations() {
        List<Vacation> vacations = vacationRepository.findAll();

        return vacations.stream().map(vacation -> mapper.map(vacation, VacationDto.class)).collect(Collectors.toList());
    }

    @Override
    public VacationDto getVacationById(Long id) {
        Optional<Vacation> byId = vacationRepository.findById(id);

        Vacation vacation = byId.orElseThrow(() -> new EntityNotFoundException("Vacation with id: " + id + " not found"));

        return mapper.map(vacation, VacationDto.class);
    }


    @Override
    public VacationDto create(VacationDto vacationDto) {
        Vacation vacation = mapper.map(vacationDto, Vacation.class);

        return mapper.map(vacationRepository.save(vacation), VacationDto.class);
    }

    @Override
    public void deleteById(Long id) {
        vacationRepository.deleteById(id);
    }

    @Override
    public VacationDto update(Long id, VacationDto vacationDto) {
        Vacation vacation = mapper.map(vacationDto, Vacation.class);
        vacation.setId(id);

        return mapper.map(vacationRepository.save(vacation), VacationDto.class);
    }
}
