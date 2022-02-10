package com.exadel.sandbox.officeFloorAttachment.controllers.service;

import com.exadel.sandbox.officeFloorAttachment.controllers.dto.AttachmentDto;
import org.springframework.stereotype.Component;

import java.util.List;

public interface AttachmentService {
    List<AttachmentDto> getAttachments();

    AttachmentDto getById(Long id);

    AttachmentDto create(AttachmentDto attachment);

    void deleteById(Long id);

    AttachmentDto update(Long id, AttachmentDto attachmentDto);

    AttachmentDto getAttachmentByMessageId(Long id);
}
