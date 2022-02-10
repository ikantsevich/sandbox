package com.exadel.sandbox.mapper;

import com.exadel.sandbox.dto.AttachmentDto;
import com.exadel.sandbox.entities.Attachment;
import org.springframework.stereotype.Component;

@Component
public interface AttachmentMapper {
    Attachment toEntity(AttachmentDto attachmentDto);

    AttachmentDto toDto(Attachment attachment);
}
