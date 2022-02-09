package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.AttachmentDto;
import com.exadel.sandbox.entities.Attachment;
import com.exadel.sandbox.mapper.AttachmentMapper;
import com.exadel.sandbox.repositories.AttachmentRepository;
import com.exadel.sandbox.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class AttachmentServiceImpl implements AttachmentService {

    AttachmentMapper attachmentMapper;

    @Autowired
    AttachmentRepository attachmentRepository;

    @Override
    public List<AttachmentDto> getAttachments() {
        List<Attachment> all = attachmentRepository.findAll();

        List<AttachmentDto> list = new ArrayList<>();

        for (Attachment attachment: all){
            list.add(attachmentMapper.toDto(attachment));
        }
        return list;

    }

    @Override
    public AttachmentDto getById(Long id) {
        Attachment attachment = attachmentRepository.getOne(id);
        return attachmentMapper.toDto(attachment);
    }

    @Override
    public AttachmentDto create(AttachmentDto attachmentDto) {
        Attachment attachment = attachmentMapper.toEntity(attachmentDto);
        Attachment save = attachmentRepository.save(attachment);
        return attachmentMapper.toDto(save);
    }

    @Override
    public void deleteById(Long id) {
        attachmentRepository.deleteById(id);
    }

    @Override
    public AttachmentDto update(Long id, AttachmentDto attachmentDto) {
        Attachment attachment = attachmentMapper.toEntity(attachmentDto);
        AttachmentDto byId = getById(id);
        if (byId == null)
            throw new RuntimeException("Attachment not found");

        attachment.setId(id);
        Attachment save = attachmentRepository.save(attachment);
        return attachmentMapper.toDto(save);
    }

    @Override
    public AttachmentDto getAttachmentByMessageId(Long id) {
        Attachment allByMessageId = attachmentRepository.getAllByMessageId(id);
        return attachmentMapper.toDto(allByMessageId);
    }
}
