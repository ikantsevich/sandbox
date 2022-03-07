package com.exadel.sandbox.notification.controller;

import com.exadel.sandbox.employee.dto.employeeDto.EmployeeResponseDto;
import com.exadel.sandbox.notification.dto.NotificationCreateDto;
import com.exadel.sandbox.notification.dto.NotificationResponseDto;
import com.exadel.sandbox.notification.entity.Notification;
import com.exadel.sandbox.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("notification")
public class NotificationController {
    private final NotificationService notificationService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    @GetMapping("list")
    ResponseEntity<List<NotificationResponseDto>> getNotificationList(){
        return notificationService.getList();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    @GetMapping("{id}")
    ResponseEntity<NotificationResponseDto> findById(@PathVariable Long id){
        return notificationService.getById(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    @GetMapping("employee/{id}")
    ResponseEntity<List<NotificationResponseDto>> findByEmployeeId(@PathVariable Long id){
        return notificationService.findByEmployeeId(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    @PostMapping()
    NotificationResponseDto sendNotification(@Valid @RequestBody NotificationCreateDto notificationCreateDto){
        return notificationService.send(notificationCreateDto);
    }
}
