package com.exadel.sandbox.employee.service;

import com.exadel.sandbox.base.BaseCrudService;
import com.exadel.sandbox.employee.entity.Employee;
import com.exadel.sandbox.employee.repository.EmployeeRepository;
import com.exadel.sandbox.employee.repository.TgInfoRepository;
import com.exadel.sandbox.exception.EntityNotFoundException;
import com.exadel.sandbox.role.entity.Role;
import com.exadel.sandbox.role.repository.RoleRepository;
import dtos.employee.dto.employeeDto.EmployeeCreateDto;
import dtos.employee.dto.employeeDto.EmployeeResponseDto;
import dtos.employee.dto.employeeDto.EmployeeUpdateDto;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class EmployeeService extends BaseCrudService<Employee, EmployeeResponseDto, EmployeeUpdateDto, EmployeeCreateDto, EmployeeRepository> {

    private final RoleRepository roleRepository;

    public EmployeeService(ModelMapper mapper, EmployeeRepository repository, TgInfoRepository tgInfoRepository, RoleRepository roleRepository) {
        super(mapper, repository);
        this.roleRepository = roleRepository;
    }

    public ResponseEntity<EmployeeResponseDto> addRole(Long id, Long roleId) {

        Employee employee = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Employee with id: " + id + " not found"));
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new EntityNotFoundException("Role with id: " + id + " not found"));

        employee.getRoles().add(role);

        EmployeeResponseDto employeeResponseDto = mapper.map(repository.save(employee), EmployeeResponseDto.class);
        return ResponseEntity.ok(employeeResponseDto);
    }
}
