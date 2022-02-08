package com.exadel.sandbox.service;

import com.exadel.sandbox.dto.AttachmentDto;
import com.exadel.sandbox.entities.Attachment;
import com.exadel.sandbox.entities.Office;

import java.util.List;

public interface AttachmentService {
    List<Attachment> getAttachments();

    Attachment getById(Long id);

    Attachment create(Attachment attachment);

    void deleteById(Long id);

    Attachment update(Long id, Attachment attachment);

    Attachment getAttachmentByMessageId(Long id);
}
