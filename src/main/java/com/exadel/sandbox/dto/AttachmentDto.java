package com.exadel.sandbox.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttachmentDto {
    private Long id;
    private String chatId;
    private Long messageId;
    private String atName;
    private Long atSize;
    private LocalDateTime atCreated;
    private LocalDateTime atModified;
}