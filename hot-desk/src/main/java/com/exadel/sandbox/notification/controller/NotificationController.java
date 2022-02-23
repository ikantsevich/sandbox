package com.exadel.sandbox.notification.controller;

import com.exadel.sandbox.notification.dto.NotificationCreateDto;
import com.exadel.sandbox.notification.dto.NotificationResponseDto;
import com.exadel.sandbox.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("notification")
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping("list")
    List<NotificationResponseDto> getNotificationList(){
        return notificationService.getAll();
    }

    @GetMapping("{id}")
    NotificationResponseDto findById(@PathVariable Long id){
        return notificationService.findById(id);
    }

    @GetMapping("employee/{id}")
    List<NotificationResponseDto> findByEmployeeId(@PathVariable Long id){
        return notificationService.findByEmployeeId(id);
    }

    @PostMapping()
    NotificationResponseDto sendNotification(@RequestBody NotificationCreateDto notificationCreateDto){
        return notificationService.send(notificationCreateDto);
    }
}
