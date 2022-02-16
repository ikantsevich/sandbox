package com.exadel.sandbox.em_notif.service.impl;

import com.exadel.sandbox.em_notif.dto.EmployeeNotificationBaseDto;
import com.exadel.sandbox.em_notif.dto.EmployeeNotificationResponseDto;
import com.exadel.sandbox.em_notif.entity.EmployeeNotification;
import com.exadel.sandbox.em_notif.repository.EmplyeeNotificationRepository;
import com.exadel.sandbox.em_notif.service.EmployeeNotificationService;
import com.exadel.sandbox.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
@Transactional
public class EmployeeNotificationServiceImpl implements EmployeeNotificationService {

    private final EmplyeeNotificationRepository emplyeeNotificationRepository;
    private final ModelMapper mapper;


    @Override
    public List<EmployeeNotificationResponseDto> getEmployeeNotifications() {

        List<EmployeeNotification> employeeNotificationList = emplyeeNotificationRepository.findAll();

        return employeeNotificationList.stream().map(this::fullMap).collect(Collectors.toList());
    }

    @Override
    public EmployeeNotificationResponseDto getEmNotifById(Long id) {

        Optional<EmployeeNotification> byId = emplyeeNotificationRepository.findById(id);

        EmployeeNotification employeeNotification = byId.orElseThrow( ()-> new EntityNotFoundException("employee_notification with id: " + id + " not found"));

        return fullMap(employeeNotification);

    }

    @Override
    public EmployeeNotificationResponseDto create(EmployeeNotificationBaseDto employeeNotificationBaseDto) {
        EmployeeNotification employeeNotification = mapper.map(employeeNotificationBaseDto , EmployeeNotification.class);

        return fullMap(emplyeeNotificationRepository.save(employeeNotification));

    }

    @Override
    public void deleteById(Long id) {
        emplyeeNotificationRepository.deleteById(id);
    }

    @Override
    public EmployeeNotificationResponseDto update(Long id, EmployeeNotificationBaseDto employeeNotificationBaseDto) {
        EmployeeNotification employeeNotification = emplyeeNotificationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("em_Notif with id: " + id + " not found"));

        mapper.map(employeeNotificationBaseDto, employeeNotification);

        return fullMap(emplyeeNotificationRepository.save(employeeNotification));
    }


    private EmployeeNotificationResponseDto fullMap(EmployeeNotification employeeNotification) {
        EmployeeNotificationResponseDto
                employeeNotificationResponseDto = mapper.map(employeeNotification, EmployeeNotificationResponseDto.class);

        return employeeNotificationResponseDto;
    }


}
