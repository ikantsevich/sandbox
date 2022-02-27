package com.exadel.sandbox.notification.controller;

import com.exadel.sandbox.notification.service.NotificationService;
import dtos.employee.dto.employeeDto.EmployeeResponseDto;
import dtos.notification.dto.NotificationCreateDto;
import dtos.notification.dto.NotificationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("notification")
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping("list")
    ResponseEntity<List<NotificationResponseDto>> getNotificationList() {
        return notificationService.getList();
    }

    @GetMapping("{id}")
    ResponseEntity<NotificationResponseDto> findById(@PathVariable Long id) {
        return notificationService.getById(id);
    }

    @GetMapping("employee/{id}")
    ResponseEntity<EmployeeResponseDto> findByEmployeeId(@PathVariable Long id) {
        return notificationService.findByEmployeeId(id);
    }

    @PostMapping()
    NotificationResponseDto sendNotification(@RequestBody NotificationCreateDto notificationCreateDto) {
        return notificationService.send(notificationCreateDto);
    }
}
