package com.exadel.sandbox.officeFloorAttachment.service;

import com.exadel.sandbox.officeFloorAttachment.dto.attachmentDto.AttachmentCreateDto;
import com.exadel.sandbox.officeFloorAttachment.dto.attachmentDto.AttachmentResponseDto;
import com.exadel.sandbox.officeFloorAttachment.dto.attachmentDto.AttachmentUpdateDto;

import java.util.List;

public interface AttachmentService {
    List<AttachmentResponseDto> getAttachments();

    AttachmentResponseDto getById(Long id);

    AttachmentResponseDto create(AttachmentCreateDto attachmentCreateDto);

    void deleteById(Long id);

    AttachmentResponseDto update(Long id, AttachmentUpdateDto attachmentUpdateDto);

    AttachmentResponseDto getAttachmentByMessageId(Long id);
}
