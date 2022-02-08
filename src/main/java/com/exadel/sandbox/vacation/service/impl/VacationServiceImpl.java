package com.exadel.sandbox.vacation.service.impl;

import com.exadel.sandbox.exception.EntityNotFoundException;
import com.exadel.sandbox.vacation.dto.VacationDto;
import com.exadel.sandbox.vacation.entities.Vacation;
import com.exadel.sandbox.vacation.repository.VacationRepository;
import com.exadel.sandbox.vacation.service.VacationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class VacationServiceImpl implements VacationService {


    @Autowired
    VacationRepository vacationRepository;

    @Override
    public List<Vacation> getVacation() {
        return vacationRepository.findAll();
    }

    @Override
    public Vacation getVacationById(Long id) {
        Optional<Vacation> byId = vacationRepository.findById(id);

        return byId.orElseThrow(() -> new EntityNotFoundException("Vacation with id: " + id + " not found"));
    }

    @Override
    public Vacation create(Vacation vacation) {
        return vacationRepository.save(vacation);
    }

    @Override
    public void deleteById(Long id) {
        vacationRepository.deleteById(id);
    }

    @Override
    public Vacation update(Long id, Vacation vacation) {
        vacation.setId(id);

        return vacationRepository.save(vacation);
    }

    @Override
    public List<VacationDto> toDtoList(List<Vacation> vacations, ModelMapper mapper) {
        return vacations.stream().map(vacation -> mapper.map(vacation, VacationDto.class)).collect(Collectors.toList());
    }
}
