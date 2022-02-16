package com.exadel.sandbox.officeFloorAttachment.controller;

import com.exadel.sandbox.officeFloorAttachment.dto.attachmentDto.AttachmentCreateDto;
import com.exadel.sandbox.officeFloorAttachment.dto.attachmentDto.AttachmentResponseDto;
import com.exadel.sandbox.officeFloorAttachment.dto.attachmentDto.AttachmentUpdateDto;
import com.exadel.sandbox.officeFloorAttachment.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attachment")
@RequiredArgsConstructor
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;

    @GetMapping("/list")
    List<AttachmentResponseDto> getAttachments() {
        return attachmentService.getAttachments();
    }

    @GetMapping("/search")
    AttachmentResponseDto getAttachment(@RequestParam("id") Long id) {
        return attachmentService.getById(id);
    }

    @GetMapping("{id}")
    AttachmentResponseDto getAttachmentByMessageId(@PathVariable("id") Long id) {
        return attachmentService.getAttachmentByMessageId(id);
    }

    @PostMapping()
    AttachmentResponseDto createAttachment(@RequestBody AttachmentCreateDto attachmentCreateDto) {
        return attachmentService.create(attachmentCreateDto);
    }

    @DeleteMapping("{id}")
    void deleteById(@PathVariable("id") Long id) {
        attachmentService.deleteById(id);
    }

    @PutMapping("{id}")
    AttachmentResponseDto updateFloor(@PathVariable("id") Long id, @RequestBody AttachmentUpdateDto attachmentUpdateDto) {
        return attachmentService.update(id, attachmentUpdateDto);
    }
}