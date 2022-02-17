package com.exadel.sandbox.em_notif.contoller;

import com.exadel.sandbox.em_notif.dto.EmployeeNotificationBaseDto;
import com.exadel.sandbox.em_notif.dto.EmployeeNotificationResponseDto;
import com.exadel.sandbox.em_notif.service.EmployeeNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employee_notification")
@RequiredArgsConstructor
public class EmployeeNotificationController{
    private final EmployeeNotificationService employeeNotificationService;



    @GetMapping("list")
    List<EmployeeNotificationResponseDto> getEmplyeeNotifications() {
        return employeeNotificationService.getEmployeeNotifications();
    }

    @GetMapping("{id}")
    EmployeeNotificationResponseDto getById(@PathVariable("id") Long id) {

        return employeeNotificationService.getEmNotifById(id);
    }

    @PostMapping()
    EmployeeNotificationResponseDto createEmployeeNotification(@RequestBody EmployeeNotificationBaseDto employeeNotificationBaseDto) {

        return employeeNotificationService.create(employeeNotificationBaseDto);
    }

    @DeleteMapping("{id}")
    void deleteEmployeeNotificationById(@PathVariable("id") Long id) {
        employeeNotificationService.deleteById(id);
    }

    @PutMapping("{id}")
    EmployeeNotificationResponseDto updateEmployeeNotification(@PathVariable("id") Long id,
                                               @RequestBody EmployeeNotificationBaseDto employeeNotificationBaseDto) {


        return employeeNotificationService.update(id, employeeNotificationBaseDto);
    }


}
