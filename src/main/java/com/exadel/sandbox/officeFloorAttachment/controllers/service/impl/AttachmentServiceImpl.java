package com.exadel.sandbox.officeFloorAttachment.controllers.service.impl;

import com.exadel.sandbox.officeFloorAttachment.controllers.dto.AttachmentDto;
import com.exadel.sandbox.officeFloorAttachment.controllers.entities.Attachment;
import com.exadel.sandbox.officeFloorAttachment.controllers.exception.EntityNotFoundException;
import com.exadel.sandbox.officeFloorAttachment.controllers.repositories.AttachmentRepository;
import com.exadel.sandbox.officeFloorAttachment.controllers.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

    private final ModelMapper mapper;

    @Autowired
    AttachmentRepository attachmentRepository;

    @Override
    public List<AttachmentDto> getAttachments() {
        List<Attachment> all = attachmentRepository.findAll();

        List<AttachmentDto> list = new ArrayList<>();

        for (Attachment attachment: all){
            list.add(mapper.map(attachment, AttachmentDto.class));
        }
        return list;

    }

    @Override
    public AttachmentDto getById(Long id) {
        Optional<Attachment> attachment = attachmentRepository.findById(id);
        Attachment attachment1 = attachment.orElseThrow(() -> new EntityNotFoundException("Attachment with id: " + id + " not found"));
        return mapper.map(attachment1, AttachmentDto.class);
    }

    @Override
    public AttachmentDto create(AttachmentDto attachmentDto) {
        Attachment attachment = mapper.map(attachmentDto, Attachment.class);
        Attachment save = attachmentRepository.save(attachment);
        return mapper.map(save, AttachmentDto.class);
    }

    @Override
    public void deleteById(Long id) {
        attachmentRepository.deleteById(id);
    }

    @Override
    public AttachmentDto update(Long id, AttachmentDto attachmentDto) {
        Attachment attachment = mapper.map(attachmentDto, Attachment.class);
        AttachmentDto byId = getById(id);
        if (byId == null)
            throw new RuntimeException("Attachment not found");

        attachment.setId(id);
        Attachment save = attachmentRepository.save(attachment);
        return mapper.map(save, AttachmentDto.class);
    }

    @Override
    public AttachmentDto getAttachmentByMessageId(Long id) {
        Attachment allByMessageId = attachmentRepository.getAllByMessageId(id);
        return mapper.map(allByMessageId, AttachmentDto.class);
    }
}
