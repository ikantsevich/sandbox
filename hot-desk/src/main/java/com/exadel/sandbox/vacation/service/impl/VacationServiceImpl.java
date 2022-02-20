package com.exadel.sandbox.vacation.service.impl;

import com.exadel.sandbox.employee.entity.Employee;
import com.exadel.sandbox.employee.repository.EmployeeRepository;
import com.exadel.sandbox.exception.EntityNotFoundException;
import com.exadel.sandbox.vacation.dto.VacationCreateDto;
import com.exadel.sandbox.vacation.dto.VacationResponseDto;
import com.exadel.sandbox.vacation.dto.VacationUpdateDto;
import com.exadel.sandbox.vacation.entities.Vacation;
import com.exadel.sandbox.vacation.repository.VacationRepository;
import com.exadel.sandbox.vacation.service.VacationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Transactional
public class VacationServiceImpl implements VacationService {

    private final VacationRepository vacationRepository;
    private final EmployeeRepository employeeRepository;
    private final ModelMapper mapper;

    @Override
    public List<VacationResponseDto> getVacations() {
        List<Vacation> vacations = vacationRepository.findAll();
        List<VacationResponseDto> vacationResponseDtos = vacations.stream().map(vacation -> {
            VacationResponseDto mapped = mapper.map(vacation, VacationResponseDto.class);
            if (vacation.getEmployee() != null)
                mapped.setEmployeeId(vacation.getEmployee().getId());
            return mapped;
        }).collect(Collectors.toList());
        return vacationResponseDtos;
    }

    @Override
    public VacationResponseDto getVacationById(Long id) {
        Optional<Vacation> byId = vacationRepository.findById(id);

        Vacation vacation = byId.orElseThrow(() -> new EntityNotFoundException("Vacation with id: " + id + " not found"));

        VacationResponseDto mapped = mapper.map(vacation, VacationResponseDto.class);
        mapped.setEmployeeId(vacation.getEmployee().getId());
        return mapped;
    }


    @Override
    public VacationResponseDto create(VacationCreateDto vacationCreateDto) {
        Vacation vacation = mapper.map(vacationCreateDto, Vacation.class);
        Employee employee = employeeRepository.findById(vacationCreateDto.getEmployeeId()).orElseThrow(() -> new EntityNotFoundException("Employee with id: " + vacationCreateDto.getEmployeeId() + " nor found"));

        vacation.setEmployee(employee);
        vacationRepository.save(vacation);
        VacationResponseDto mapped = mapper.map(vacation, VacationResponseDto.class);
        mapped.setEmployeeId(vacation.getEmployee().getId());
        return mapped;
    }

    @Override
    public void deleteById(Long id) {
        vacationRepository.deleteById(id);
    }

    @Override
    public VacationResponseDto update(Long id, VacationUpdateDto vacationUpdateDto) {
        Vacation vacation = vacationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Vacation with id: " + id + " not found"));
        Employee employee = employeeRepository.findById(vacationUpdateDto.getEmployeeId()).orElseThrow(() -> new EntityNotFoundException("employee with id" + vacationUpdateDto.getEmployeeId() + " not found"));

        mapper.map(vacationUpdateDto, vacation);
        vacation.setEmployee(employee);
        vacationRepository.save(vacation);
        VacationResponseDto map = mapper.map(vacation, VacationResponseDto.class);
        map.setEmployeeId(vacation.getEmployee().getId());
        return map;
    }
}
