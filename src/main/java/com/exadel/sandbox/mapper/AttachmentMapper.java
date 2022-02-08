package com.exadel.sandbox.mapper;

import com.exadel.sandbox.dto.AttachmentDto;
import com.exadel.sandbox.entities.Attachment;

public interface AttachmentMapper {
    Attachment toEntity(AttachmentDto attachmentDto);
    AttachmentDto toDto(Attachment attachment);
}
