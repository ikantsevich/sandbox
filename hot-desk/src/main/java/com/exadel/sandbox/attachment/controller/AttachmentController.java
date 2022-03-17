package com.exadel.sandbox.attachment.controller;

import com.exadel.sandbox.attachment.dto.AttachmentResponseDto;
import com.exadel.sandbox.attachment.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/attachment")
@RequiredArgsConstructor
public class AttachmentController {

    private final AttachmentService attachmentService;

    @GetMapping("{id}")
    void getAttachment(@PathVariable Long id, HttpServletResponse httpServletResponse) {
        attachmentService.getById(id, httpServletResponse);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MAPPER')")
    @PostMapping()
    AttachmentResponseDto createAttachment(MultipartHttpServletRequest request) {
        return attachmentService.create(request);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MAPPER')")
    @DeleteMapping("{id}")
    void deleteById(@PathVariable("id") Long id) {
        attachmentService.deleteById(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MAPPER')")
    @PutMapping("{id}")
    void update(@PathVariable Long id, MultipartHttpServletRequest request) {
        attachmentService.update(id, request);
    }
}