package com.exadel.sandbox.officeFloorAttachment.service.impl;

import com.exadel.sandbox.officeFloorAttachment.dto.attachmentDto.AttachmentCreateDto;
import com.exadel.sandbox.officeFloorAttachment.dto.attachmentDto.AttachmentResponseDto;
import com.exadel.sandbox.officeFloorAttachment.dto.attachmentDto.AttachmentUpdateDto;
import com.exadel.sandbox.officeFloorAttachment.entities.Attachment;
import com.exadel.sandbox.exception.EntityNotFoundException;
import com.exadel.sandbox.officeFloorAttachment.repositories.AttachmentRepository;
import com.exadel.sandbox.officeFloorAttachment.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

    private final ModelMapper mapper;

    @Autowired
    AttachmentRepository attachmentRepository;

    @Override
    public List<AttachmentResponseDto> getAttachments() {
        List<Attachment> attachmentList = attachmentRepository.findAll();

        return attachmentList.stream().map(attachment -> mapper.map(attachment, AttachmentResponseDto.class)).collect(Collectors.toList());
    }

    @Override
    public AttachmentResponseDto getById(Long id) {
        Optional<Attachment> attachment = attachmentRepository.findById(id);
        Attachment attachment1 = attachment.orElseThrow(() -> new EntityNotFoundException("Attachment with id: " + id + " not found"));
        return mapper.map(attachment1, AttachmentResponseDto.class);
    }

    @Override
    public AttachmentResponseDto create(AttachmentCreateDto attachmentCreateDto) {
        Attachment attachment = mapper.map(attachmentCreateDto, Attachment.class);
        Attachment savedAttachment = attachmentRepository.save(attachment);
        return mapper.map(savedAttachment, AttachmentResponseDto.class);
    }

    @Override
    public void deleteById(Long id) {
        attachmentRepository.deleteById(id);
    }

    @Override
    public AttachmentResponseDto update(Long id, AttachmentUpdateDto attachmentUpdateDto) {
        Attachment attachment = mapper.map(attachmentUpdateDto, Attachment.class);
        AttachmentResponseDto attachmentResponseDto = getById(id);
        if (attachmentResponseDto == null)
            throw new EntityNotFoundException("Attachment not found with this " + id);

        attachment.setId(id);
        Attachment savedAttachment = attachmentRepository.save(attachment);
        return mapper.map(savedAttachment, AttachmentResponseDto.class);
    }

    @Override
    public AttachmentResponseDto getAttachmentByMessageId(Long id) {
        Attachment allByMessageId = attachmentRepository.getAllByMessageId(id);
        return mapper.map(allByMessageId, AttachmentResponseDto.class);
    }
}
