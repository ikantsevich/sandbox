package com.exadel.sandbox.notification.controller;


import com.exadel.sandbox.notification.dto.NotificationBaseDto;
import com.exadel.sandbox.notification.dto.NotificationResponseDto;
import com.exadel.sandbox.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("list")
    List<NotificationResponseDto> getNotifications() {
        return notificationService.getNotifications();
    }

    @GetMapping("{id}")
    NotificationResponseDto getById(@PathVariable("id") Long id) {

        return notificationService.getNotificationById(id);
    }

    @PostMapping()
    NotificationResponseDto createSeat(@RequestBody NotificationBaseDto notificationBaseDto) {

        return notificationService.create(notificationBaseDto);
    }

    @DeleteMapping("{id}")
    void deleteNotifById(@PathVariable("id") Long id) {
        notificationService.deleteById(id);
    }

    @PutMapping("{id}")
    NotificationResponseDto updateNotification(@PathVariable("id") Long id,
                               @RequestBody NotificationBaseDto notificationBaseDto) {


        return notificationService.update(id, notificationBaseDto);
    }




}
