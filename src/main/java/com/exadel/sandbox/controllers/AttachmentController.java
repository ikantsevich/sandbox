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

    @GetMapping("list")
    List<AttachmentDto> getAttachments() {
        return attachmentService.getAttachments();
    }

    @GetMapping("search")
    AttachmentDto getAttachment(@RequestParam("id") Long id) {
        return attachmentService.getById(id);
    }

    @GetMapping("{id}")
    AttachmentDto getAttachmentByMessageId(@PathVariable("id") Long id) {
        return attachmentService.getAttachmentByMessageId(id);
    }

    @PutMapping()
    AttachmentDto createAttachment(@RequestBody AttachmentDto attachmentDto) {
        return attachmentService.create(attachmentDto);
    }

    @DeleteMapping("{id}")
    void deleteById(@PathVariable("id") Long id) {
        attachmentService.deleteById(id);
    }

    @PostMapping("{id}")
    AttachmentDto updateFloor(@PathVariable("id") Long id, @RequestBody AttachmentDto attachmentDto) {
        return attachmentService.update(id, attachmentDto);
    }
}
