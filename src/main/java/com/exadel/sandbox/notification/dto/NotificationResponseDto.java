package com.exadel.sandbox.notification.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class NotificationResponseDto extends NotificationBaseDto {
    private Long id;
    private LocalDateTime created;
    private LocalDateTime modified;
}
