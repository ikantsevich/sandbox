package com.exadel.sandbox.officeFloor.service;

import com.exadel.sandbox.attachment.entity.Attachment;
import com.exadel.sandbox.attachment.repository.AttachmentRepository;
import com.exadel.sandbox.attachment.service.AttachmentService;
import com.exadel.sandbox.base.BaseCrudService;
import com.exadel.sandbox.exception.EntityNotFoundException;
import com.exadel.sandbox.officeFloor.entities.Floor;
import com.exadel.sandbox.officeFloor.repositories.FloorRepository;
import dtos.attachment.dto.AttachmentResponseDto;
import dtos.officeFloor.dto.floorDto.FloorCreateDto;
import dtos.officeFloor.dto.floorDto.FloorResponseDto;
import dtos.officeFloor.dto.floorDto.FloorUpdateDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
@Transactional
public class FloorService extends BaseCrudService<Floor, FloorResponseDto, FloorUpdateDto, FloorCreateDto, FloorRepository> {

    private final AttachmentRepository attachmentRepository;
    private final AttachmentService attachmentService;

    public FloorService(ModelMapper mapper, FloorRepository repository, AttachmentRepository attachmentRepository, AttachmentService attachmentService) {
        super(mapper, repository);
        this.attachmentRepository = attachmentRepository;
        this.attachmentService = attachmentService;
    }

    public ResponseEntity<List<FloorResponseDto>> getFloorsByOfId(Long id) {
        List<Floor> floors = repository.findFloorsByOfficeId(id);
        return ResponseEntity.ok(mapper.map(floors, new TypeToken<List<FloorResponseDto>>() {
        }.getType()));
    }

    public void setMap(Long id, MultipartHttpServletRequest request) {
        AttachmentResponseDto attachmentResponseDto = attachmentService.create(request);
        Attachment attachment = attachmentRepository.findAttachmentByFloorId(attachmentResponseDto.getId());
        Floor floor = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Floor with id " + id + " not found"));
        floor.setAttachment(attachment);
        repository.save(floor);
    }

    public void getMap(Long id, HttpServletResponse response) {
        Attachment attachmentByFloorId = attachmentRepository.findAttachmentByFloorId(id);
        attachmentService.getById(attachmentByFloorId.getId(), response);
    }
}
