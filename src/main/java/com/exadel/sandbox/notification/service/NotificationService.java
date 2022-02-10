package com.exadel.sandbox.notification.service;

import com.exadel.sandbox.notification.dto.NotificationBaseDto;
import com.exadel.sandbox.notification.dto.NotificationResponseDto;
import com.exadel.sandbox.notification.entity.Notification;

import java.util.List;

public interface NotificationService {

    List<NotificationResponseDto> getNotifications();

    NotificationResponseDto getNotificationById(Long id);

    NotificationResponseDto create(NotificationBaseDto notificationBaseDto);

    void deleteById(Long id);

    NotificationResponseDto update(Long id, NotificationBaseDto notificationBaseDto);

}
