package com.exadel.sandbox.vacation.service.impl;

import com.exadel.sandbox.employee.dto.employeeDto.EmployeeResponseDto;
import com.exadel.sandbox.employee.entity.Employee;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Transactional
public class VacationServiceImpl implements VacationService {

    private final VacationRepository vacationRepository;
    private final ModelMapper mapper;

    @Override
    public List<VacationResponseDto> getVacations() {
        List<Vacation> vacations = vacationRepository.findAll();

        List<VacationResponseDto> vacationResponseDtos = new ArrayList<>();

        for (Vacation vacation: vacations) {
            vacationResponseDtos.add(fullMap(vacation));
        }

        return vacationResponseDtos;
    }

    @Override
    public VacationResponseDto getVacationById(Long id) {
        Optional<Vacation> byId = vacationRepository.findById(id);

        Vacation vacation = byId.orElseThrow(() -> new EntityNotFoundException("Vacation with id: " + id + " not found"));

        return fullMap(vacation);
    }


    @Override
    public VacationResponseDto create(VacationCreateDto vacationCreateDto) {
        Vacation vacation = mapper.map(vacationCreateDto, Vacation.class);
        if (vacation.getEmployee() != null)
            vacation.setEmployee(mapper.map(vacationCreateDto.getEmployeeResponseDto(), Employee.class));

        return fullMap(vacationRepository.save(vacation));
    }

    @Override
    public void deleteById(Long id) {
        vacationRepository.deleteById(id);
    }

    @Override
    public VacationResponseDto update(Long id, VacationUpdateDto vacationUpdateDto) {

        Vacation vacation = vacationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Vacation with id: " + id + " not found"));

        mapper.map(vacationUpdateDto, vacation);

        return fullMap(vacationRepository.save(vacation));
    }

    private VacationResponseDto fullMap(Vacation vacation) {
        VacationResponseDto vacationResponseDto = mapper.map(vacation, VacationResponseDto.class);
        EmployeeResponseDto employeeResponseDto = null;

        if (vacation.getEmployee() != null)
            employeeResponseDto = mapper.map(vacation.getEmployee(), EmployeeResponseDto.class);

        vacationResponseDto.setEmployeeResponseDto(employeeResponseDto);

        return vacationResponseDto;
    }
}
