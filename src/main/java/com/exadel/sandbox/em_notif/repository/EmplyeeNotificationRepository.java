package com.exadel.sandbox.em_notif.repository;

import com.exadel.sandbox.em_notif.entity.EmployeeNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmplyeeNotificationRepository extends JpaRepository<EmployeeNotification, Long> {
}
