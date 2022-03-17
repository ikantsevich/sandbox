package com.exadel.sandbox.employee.service;

import com.exadel.sandbox.base.BaseCrudService;
import com.exadel.sandbox.booking.service.BookingService;
import com.exadel.sandbox.employee.dto.employeeDto.EmployeeCreateDto;
import com.exadel.sandbox.employee.dto.employeeDto.EmployeeResponseDto;
import com.exadel.sandbox.employee.dto.employeeDto.EmployeeUpdateDto;
import com.exadel.sandbox.employee.entity.Employee;
import com.exadel.sandbox.employee.repository.EmployeeRepository;
import com.exadel.sandbox.employee.repository.TgInfoRepository;
import com.exadel.sandbox.exception.exceptions.EntityNotFoundException;
import com.exadel.sandbox.exception.exceptions.ForbiddenException;
import com.exadel.sandbox.role.entity.Role;
import com.exadel.sandbox.role.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class EmployeeService extends BaseCrudService<Employee, EmployeeResponseDto, EmployeeUpdateDto, EmployeeCreateDto, EmployeeRepository> {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public EmployeeService(ModelMapper mapper, EmployeeRepository repository, TgInfoRepository tgInfoRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        super(mapper, repository);
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<EmployeeResponseDto> create(EmployeeCreateDto employeeCreateDto) {
        Employee employee = mapper.map(employeeCreateDto, Employee.class);
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));

        employee = repository.save(employee);
        return ResponseEntity.ok(mapper.map(employee, EmployeeResponseDto.class));
    }

    public ResponseEntity<EmployeeResponseDto> addRole(Long id, Long roleId) {

        Employee employee = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Employee with id: " + id + " not found"));
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new EntityNotFoundException("Role with id: " + id + " not found"));

        employee.getRoles().add(role);

        EmployeeResponseDto employeeResponseDto = mapper.map(repository.save(employee), EmployeeResponseDto.class);
        return ResponseEntity.ok(employeeResponseDto);
    }

    public ResponseEntity<EmployeeResponseDto> getByChatId(String chatId) {
        Employee employee = repository.findEmployeeByTgInfoChatId(chatId).orElseThrow(() -> new EntityNotFoundException("Employee with id: " + chatId + " not found"));

        return ResponseEntity.ok(mapper.map(employee, EmployeeResponseDto.class));
    }

    public ResponseEntity<List<LocalDate>> getEmployeeBookedDates(Long id) {
        LocalDate today = LocalDate.now();
        LocalDate finishLimit = today.plusMonths(BookingService.MAX_MONTH);

        List<LocalDate> employeeBookedDates = repository.findEmployeeBookedDates(id, today, finishLimit);

        return ResponseEntity.ok(employeeBookedDates);
    }

    public ResponseEntity<List<LocalDate>> getEmployeeBookedDatesAll(Long id) {
        List<LocalDate> employeeBookedDates = repository.findEmployeeBookedDates(id);

        return ResponseEntity.ok(employeeBookedDates);
    }

    public ResponseEntity<EmployeeResponseDto> getById(Long id, Principal principal) {
        checkPrivacy(id, principal);
        return super.getById(id);
    }

    public ResponseEntity<List<LocalDate>> getEmployeeBookedDates(Long id, Principal principal) {
        checkPrivacy(id, principal);
        return getEmployeeBookedDates(id);
    }

    public ResponseEntity<List<LocalDate>> getEmployeeBookedDatesAll(Long id, Principal principal) {
        checkPrivacy(id, principal);
        return  getEmployeeBookedDatesAll(id);
    }

    public void checkPrivacy(Long id, Principal principal) {
        Employee employee = repository.findEmployeeByEmail(principal.getName()).orElseThrow(() -> new EntityNotFoundException("you are not our employee"));

        if (!id.equals(employee.getId())){
            employee.getRoles().forEach(role -> {
                if (role.getName().equals("ROLE_EMPLOYEE"))
                    throw new ForbiddenException();
            });
        }
    }
}
