package com.exadel.sandbox.em_notif.service;


import com.exadel.sandbox.em_notif.dto.EmployeeNotificationBaseDto;
import com.exadel.sandbox.em_notif.dto.EmployeeNotificationResponseDto;

import java.util.List;

public interface EmployeeNotificationService {



    List<EmployeeNotificationResponseDto> getEmployeeNotifications();

    EmployeeNotificationResponseDto getEmNotifById(Long id);

    EmployeeNotificationResponseDto create(EmployeeNotificationBaseDto employeeNotificationBaseDto);

    void deleteById(Long id);

    EmployeeNotificationResponseDto update(Long id, EmployeeNotificationBaseDto employeeNotificationBaseDto);
}
