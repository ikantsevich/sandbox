package com.exadel.sandbox.controllers;

import com.exadel.sandbox.dto.AttachmentDto;
import com.exadel.sandbox.entities.Attachment;
import com.exadel.sandbox.mapper.AttachmentMapper;
import com.exadel.sandbox.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("attachment")
@RequiredArgsConstructor
public class AttachmentController {
    private AttachmentService attachmentService;
    private AttachmentMapper attachmentMapper;

    @GetMapping("list")
    List<AttachmentDto> getAttachments(){
        List<Attachment> attachments = attachmentService.getAttachments();
        return attachments.stream().map(attachmentMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("search")
    AttachmentDto getAttachment(@RequestParam("id") Long id){
        Attachment attachment = attachmentService.getById(id);
        return attachmentMapper.toDto(attachment);
    }

    @GetMapping("{id}")
    AttachmentDto getAttachmentByMessageId(@PathVariable("id") Long id){
        Attachment attachment = attachmentService.getAttachmentByMessageId(id);
        return attachmentMapper.toDto(attachment);
    }

    @PutMapping()
    AttachmentDto createAttachment(@RequestBody AttachmentDto attachmentDto){
        Attachment attachment = attachmentMapper.toEntity(attachmentDto);
        attachment = attachmentService.create(attachment);
        return attachmentMapper.toDto(attachment);
    }

    @DeleteMapping("{id}")
    void deleteById(@PathVariable("id") Long id){
        attachmentService.deleteById(id);
    }

    @PostMapping("{id}")
    AttachmentDto updateFloor(@PathVariable("id") Long id,
                         @RequestBody AttachmentDto attachmentDto ){
        Attachment attachment = attachmentMapper.toEntity(attachmentDto);
        attachment = attachmentService.update(id, attachment);
        return attachmentMapper.toDto(attachment);
    }
}
