package com.exadel.sandbox.officeFloorAttachment.dto.attachmentDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentCreateDto {
    private String chatId;
    private Long messageId;
    private String name;
}
