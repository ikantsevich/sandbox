package com.exadel.sandbox.attachment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentResponseDto {
    private Long id;
    private String originalName;
    private Long size;
    private LocalDateTime atCreated;
    private LocalDateTime atModified;
}
