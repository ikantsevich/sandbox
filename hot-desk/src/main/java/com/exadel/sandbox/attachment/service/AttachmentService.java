package com.exadel.sandbox.attachment.service;

import com.exadel.sandbox.attachment.dto.AttachmentResponseDto;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;

public interface AttachmentService {

    void getById(Long id, HttpServletResponse httpServletResponse);

    AttachmentResponseDto create(MultipartHttpServletRequest request);

    void deleteById(Long id);

    void update(Long id, MultipartHttpServletRequest request);
}
