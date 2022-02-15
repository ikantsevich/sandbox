package com.exadel.sandbox.officeFloorAttachment.dto.attachmentDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentResponseDto {
    private Long id;
    private String chatId;
    private Long messageId;
    private String name;
    private Long size;
    private LocalDateTime atCreated;
    private LocalDateTime atModified;
}
