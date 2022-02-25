package com.exadel.sandbox.notification.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonPropertyOrder({"id", "employeeId"})
public class NotificationResponseDto extends NotificationBaseDto {
    private Long id;
    private Long employeeId;
    private LocalDateTime created;
    private LocalDateTime modified;
}
