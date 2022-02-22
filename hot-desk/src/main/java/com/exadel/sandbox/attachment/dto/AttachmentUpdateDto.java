package com.exadel.sandbox.attachment.dto;

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
    private Long size;
    private LocalDateTime atModified;
}
