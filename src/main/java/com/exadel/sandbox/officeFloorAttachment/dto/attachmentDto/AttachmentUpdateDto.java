package com.exadel.sandbox.officeFloorAttachment.dto.attachmentDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentUpdateDto {
    private Long messageId;
    private String atName;
    private Long atSize;
    private LocalDateTime atModified;
}
