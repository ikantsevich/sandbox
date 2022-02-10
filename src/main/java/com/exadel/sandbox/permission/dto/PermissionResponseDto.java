package com.exadel.sandbox.permission.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PermissionResponseDto extends PermissionBaseDto {
    private Long id;
    private LocalDateTime created;
    private LocalDateTime modified;
}
