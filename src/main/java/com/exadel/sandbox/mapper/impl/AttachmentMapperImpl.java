package com.exadel.sandbox.mapper.impl;

import com.exadel.sandbox.dto.AttachmentDto;
import com.exadel.sandbox.entities.Attachment;
import com.exadel.sandbox.mapper.AttachmentMapper;

public class AttachmentMapperImpl implements AttachmentMapper {
    @Override
    public Attachment toEntity(AttachmentDto attachmentDto) {
        return new Attachment(
                attachmentDto.getId(),
                attachmentDto.getChatId(),
                attachmentDto.getMessageId(),
                attachmentDto.getAtName(),
                attachmentDto.getAtSize(),
                attachmentDto.getAtCreated(),
                attachmentDto.getAtModified()
        );
    }

    @Override
    public AttachmentDto toDto(Attachment attachment) {
        return new AttachmentDto(
                attachment.getId(),
                attachment.getChatId(),
                attachment.getMessageId(),
                attachment.getAtName(),
                attachment.getAtSize(),
                attachment.getAtCreated(),
                attachment.getAtModified()
        );
    }
}
