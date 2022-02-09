package com.exadel.sandbox.service;

import com.exadel.sandbox.dto.AttachmentDto;
import com.exadel.sandbox.entities.Attachment;
import com.exadel.sandbox.entities.Office;

import java.util.List;

public interface AttachmentService {
    List<AttachmentDto> getAttachments();

    AttachmentDto getById(Long id);

    AttachmentDto create(AttachmentDto attachment);

    void deleteById(Long id);

    AttachmentDto update(Long id, AttachmentDto attachmentDto);

    AttachmentDto getAttachmentByMessageId(Long id);
}
