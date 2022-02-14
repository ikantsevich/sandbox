package com.exadel.sandbox.em_notif.dto;

import com.exadel.sandbox.employee.dto.employeeDto.EmployeeResponseDto;
import com.exadel.sandbox.employee.entity.Employee;
import com.exadel.sandbox.notification.dto.NotificationResponseDto;
import com.exadel.sandbox.notification.entity.Notification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
//@EqualsAndHashCode(callSuper = true)
public class EmployeeNotificationBaseDto {

    //FIXME employee id should be from employee dto
    private EmployeeResponseDto employeeResponseDto;

    //FIXME
    private NotificationResponseDto notificationResponseDto;

}
