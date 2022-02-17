package com.exadel.sandbox.employee.service.impl;

import com.exadel.sandbox.employee.dto.employeeDto.EmployeeCreateDto;
import com.exadel.sandbox.employee.dto.employeeDto.EmployeeResponseDto;
import com.exadel.sandbox.employee.dto.employeeDto.EmployeeUpdateDto;
import com.exadel.sandbox.employee.dto.tgInfoDto.TgInfoResponseDto;
import com.exadel.sandbox.employee.entity.Employee;
import com.exadel.sandbox.employee.entity.TgInfo;
import com.exadel.sandbox.employee.repository.EmployeeRepository;
import com.exadel.sandbox.employee.service.EmployeeService;
import com.exadel.sandbox.exception.EntityNotFoundException;
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
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper mapper;

    @Override
    public List<EmployeeResponseDto> getEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        List<EmployeeResponseDto> employeeResponseDtos = new ArrayList<>();

        for (Employee employee : employees) {
            employeeResponseDtos.add(fullMap(employee));
        }


        return employeeResponseDtos;
    }

    @Override
    public EmployeeResponseDto getEmployeeByID(Long id) {
        Optional<Employee> byId = employeeRepository.findById(id);

        Employee employee = byId.orElseThrow(() -> new EntityNotFoundException("Employee with id: " + id + " not found"));

        return fullMap(employee);
    }

    @Override
    public EmployeeResponseDto create(EmployeeCreateDto employeeCreateDto) {
        Employee employee = mapper.map(employeeCreateDto, Employee.class);

        if (employeeCreateDto.getTgInfoResponseDto() != null)
            employee.setTgInfo(mapper.map(employeeCreateDto.getTgInfoResponseDto(), TgInfo.class));
        return fullMap(employeeRepository.save(employee));
    }

    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public EmployeeResponseDto update(Long id, EmployeeUpdateDto employeeUpdateDto) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Employee with id: " + id + " not found"));

        mapper.map(employeeUpdateDto, employee);

        return fullMap(employeeRepository.save(employee));
    }

    private EmployeeResponseDto fullMap(Employee employee) {
        EmployeeResponseDto employeeResponseDto = mapper.map(employee, EmployeeResponseDto.class);

        TgInfoResponseDto tgInfoResponseDto = null;
        if (employee.getTgInfo() != null)
            tgInfoResponseDto = mapper.map(employee.getTgInfo(), TgInfoResponseDto.class);

        employeeResponseDto.setTgInfoResponseDto(tgInfoResponseDto);

        return employeeResponseDto;
    }
}
