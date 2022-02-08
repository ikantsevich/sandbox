package com.exadel.sandbox.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TgInfoDto {
    private Long id;
    private String chatId;
    private String chatState;
    private String username;
    private LocalDateTime created;
    private LocalDateTime modified;
}
