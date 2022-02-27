package com.exadel.sandbox.attachment.service;

import com.exadel.sandbox.attachment.entity.Attachment;
import com.exadel.sandbox.attachment.repository.AttachmentRepository;
import com.exadel.sandbox.exception.EntityNotFoundException;
import dtos.attachment.dto.AttachmentResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AttachmentService {

    private final AttachmentRepository attachmentRepository;
    private final ModelMapper mapper;
    private final String attachmentPath = "hot-desk/src/main/resources/static/";

    public void getById(Long id, HttpServletResponse response) {
        Attachment attachment = attachmentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Attachment with id: " + id + " not found"));
        response.setContentType(attachment.getContentType());
        try {
            FileCopyUtils.copy(new FileInputStream(attachmentPath + attachment.getName()), response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AttachmentResponseDto create(MultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileNames();
        Attachment attachment = new Attachment();
        String fileName = fileNames.next();
        MultipartFile file = request.getFile(fileName);
        if (file != null) {
            String fileName1 = file.getOriginalFilename();
            attachment.setContentType(file.getContentType());
            attachment.setSize(file.getSize());
            attachment.setOriginalName(fileName1);
            assert fileName1 != null;
            String name = UUID.randomUUID() + "." + fileName1.split("\\.")[fileName1.split("\\.").length - 1];
            attachment.setName(name);
            attachmentRepository.save(attachment);
            try {
                Files.copy(file.getInputStream(), Paths.get(attachmentPath + name));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mapper.map(attachment, AttachmentResponseDto.class);
    }

    public void deleteById(Long id) {
        Attachment attachment = attachmentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Attachment with id: " + id + " not found"));
        File file = new File(attachmentPath + attachment.getName());
        file.delete();
        attachmentRepository.delete(attachment);
    }

    public void update(Long id, MultipartHttpServletRequest request) {
        Attachment attachment = attachmentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Attachment with id: " + id + " not found"));

        File deletedFile = new File(attachmentPath + attachment.getName());
        deletedFile.delete();

        MultipartFile file = request.getFile(request.getFileNames().next());
        if (file != null) {
            String fileName1 = file.getOriginalFilename();
            attachment.setContentType(file.getContentType());
            attachment.setSize(file.getSize());
            attachment.setOriginalName(fileName1);
            assert fileName1 != null;
            String name = UUID.randomUUID() + "." + fileName1.split("\\.")[fileName1.split("\\.").length - 1];
            attachment.setName(name);
            attachmentRepository.save(attachment);
            try {
                Files.copy(file.getInputStream(), Paths.get(attachmentPath + name));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
