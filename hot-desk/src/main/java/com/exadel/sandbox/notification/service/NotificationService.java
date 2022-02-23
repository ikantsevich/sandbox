package com.exadel.sandbox.notification.service;

import com.exadel.sandbox.notification.dto.NotificationCreateDto;
import com.exadel.sandbox.notification.dto.NotificationResponseDto;

import java.util.List;

public interface NotificationService {
    List<NotificationResponseDto> getAll();

    NotificationResponseDto findById(Long id);

    NotificationResponseDto send(NotificationCreateDto notificationCreateDto);

    List<NotificationResponseDto> findByEmployeeId(Long id);
}
