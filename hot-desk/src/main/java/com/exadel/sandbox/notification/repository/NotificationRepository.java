package com.exadel.sandbox.notification.repository;

import com.exadel.sandbox.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findNotificationsByEmployeeId(Long id);
}
