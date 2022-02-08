package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.entities.Attachment;
import com.exadel.sandbox.entities.Floor;
import com.exadel.sandbox.repositories.AttachmentRepository;
import com.exadel.sandbox.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentRepository attachmentRepository;

    @Override
    public List<Attachment> getAttachments() {
        return attachmentRepository.findAll();
    }

    @Override
    public Attachment getById(Long id) {
        return getById(id);
    }

    @Override
    public Attachment create(Attachment attachment) {
        return attachmentRepository.save(attachment);
    }

    @Override
    public void deleteById(Long id) {
        attachmentRepository.deleteById(id);
    }

    @Override
    public Attachment update(Long id, Attachment attachment) {
        Attachment byId = getById(id);
        if (byId == null)
            throw new RuntimeException("Attachment not found");

        attachment.setId(id);
        return attachmentRepository.save(attachment);
    }

    @Override
    public Attachment getAttachmentByMessageId(Long id) {
        return attachmentRepository.getAllByMessageId(id);
    }
}
