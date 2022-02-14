package com.exadel.sandbox.em_notif.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;



@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EmployeeNotificationResponseDto extends EmployeeNotificationBaseDto {
    private Long id;
    private LocalDateTime created;
    private LocalDateTime modified;

}
