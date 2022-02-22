package com.exadel.sandbox.employee.service.impl;

import com.exadel.sandbox.employee.dto.employeeDto.EmployeeCreateDto;
import com.exadel.sandbox.employee.dto.employeeDto.EmployeeResponseDto;
import com.exadel.sandbox.employee.dto.employeeDto.EmployeeUpdateDto;
import com.exadel.sandbox.employee.dto.tgInfoDto.TgInfoResponseDto;
import com.exadel.sandbox.employee.entity.Employee;
import com.exadel.sandbox.employee.entity.TgInfo;
import com.exadel.sandbox.employee.repository.EmployeeRepository;
import com.exadel.sandbox.employee.repository.TgInfoRepository;
import com.exadel.sandbox.employee.service.EmployeeService;
import com.exadel.sandbox.exception.EntityNotFoundException;
import com.exadel.sandbox.role.dto.RoleResponseDto;
import com.exadel.sandbox.role.entity.Role;
import com.exadel.sandbox.role.repository.RoleRepository;
import com.exadel.sandbox.vacation.dto.VacationResponseDto;
import com.exadel.sandbox.vacation.entities.Vacation;
import com.exadel.sandbox.vacation.repository.VacationRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final TgInfoRepository tgInfoRepository;
    private final RoleRepository roleRepository;
    private final VacationRepository vacationRepository;
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
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Employee with id: " + id + " not found"));

        return fullMap(employee);
    }

    @Override
    public EmployeeResponseDto create(EmployeeCreateDto employeeCreateDto) {
        Employee employee = mapper.map(employeeCreateDto, Employee.class);

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

    @Override
    public EmployeeResponseDto setTgInfo(Long id, Long tgInfoId) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Employee with id: " + id + " not found"));
        TgInfo tgInfo = tgInfoRepository.findById(tgInfoId).orElseThrow(() -> new EntityNotFoundException("Telegram info with id: " + id + " not found"));

        employee.setTgInfo(tgInfo);
        Employee newEmployee = employeeRepository.save(employee);
        return fullMap(newEmployee);
    }

    @Override
    public EmployeeResponseDto addRole(Long id, Long roleId) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Employee with id: " + id + " not found"));
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new EntityNotFoundException("Role with id: " + id + " not found"));
        employee.getRoles().add(role);
        Employee newEmployee = employeeRepository.save(employee);
        return fullMap(newEmployee);
    }

    private EmployeeResponseDto fullMap(Employee employee) {
        EmployeeResponseDto employeeResponseDto = mapper.map(employee, EmployeeResponseDto.class);
        if (employee.getRoles() != null) {
            List<Role> roles = employee.getRoles();

            List<RoleResponseDto> roleResponseDtos = roles.stream().map(role -> mapper.map(role, RoleResponseDto.class)).collect(Collectors.toList());
            employeeResponseDto.setRoles(roleResponseDtos);
        }

        if (employee.getVacations() != null) {
            Set<Vacation> vacations = employee.getVacations();

            List<VacationResponseDto> vacationResponseDtos = vacations.stream().map(vacation -> {
                VacationResponseDto map = mapper.map(vacation, VacationResponseDto.class);
                map.setEmployeeId(vacation.getEmployee().getId());
                return map;
            }).collect(Collectors.toList());

            employeeResponseDto.setVacations(vacationResponseDtos);
        }

        TgInfoResponseDto tgInfoResponseDto = null;
        if (employee.getTgInfo() != null)
            tgInfoResponseDto = mapper.map(employee.getTgInfo(), TgInfoResponseDto.class);

        employeeResponseDto.setTgInfoResponseDto(tgInfoResponseDto);

        return employeeResponseDto;
    }
}
