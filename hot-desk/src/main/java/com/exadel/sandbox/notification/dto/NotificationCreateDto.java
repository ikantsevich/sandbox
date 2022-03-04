package com.exadel.sandbox.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotificationCreateDto extends NotificationBaseDto{
    @NotNull(message = "cannot be null")
    private Long employeeId;
}
