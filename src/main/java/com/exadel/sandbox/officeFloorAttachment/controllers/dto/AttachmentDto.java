package com.exadel.sandbox.officeFloorAttachment.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentDto {
    private Long id;
    private String chatId;
    private Long messageId;
    private String atName;
    private Long atSize;
    private LocalDateTime atCreated;
    private LocalDateTime atModified;
}
