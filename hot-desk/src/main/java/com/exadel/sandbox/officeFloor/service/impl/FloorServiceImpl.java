package com.exadel.sandbox.officeFloor.service.impl;

import com.exadel.sandbox.attachment.dto.AttachmentResponseDto;
import com.exadel.sandbox.attachment.entity.Attachment;
import com.exadel.sandbox.attachment.repository.AttachmentRepository;
import com.exadel.sandbox.attachment.service.AttachmentService;
import com.exadel.sandbox.exception.EntityNotFoundException;
import com.exadel.sandbox.officeFloor.dto.floorDto.FloorCreateDto;
import com.exadel.sandbox.officeFloor.dto.floorDto.FloorResponseDto;
import com.exadel.sandbox.officeFloor.dto.floorDto.FloorUpdateDto;
import com.exadel.sandbox.officeFloor.dto.officeDto.OfficeResponseDto;
import com.exadel.sandbox.officeFloor.entities.Floor;
import com.exadel.sandbox.officeFloor.entities.Office;
import com.exadel.sandbox.officeFloor.repositories.FloorRepository;
import com.exadel.sandbox.officeFloor.repositories.OfficeRepository;
import com.exadel.sandbox.officeFloor.service.FloorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Transactional
public class FloorServiceImpl implements FloorService {

    private final FloorRepository floorRepository;
    private final ModelMapper mapper;
    private final OfficeRepository officeRepository;
    private final AttachmentRepository attachmentRepository;
    private final AttachmentService attachmentService;

    @Override
    public List<FloorResponseDto> getFloors() {
        List<Floor> floorList = floorRepository.findAll();

        return floorList.stream().map(this::floorToFloorResponseDto).collect(Collectors.toList());
    }

    @Override
    public FloorResponseDto getById(Long id) {
        Optional<Floor> floor1 = floorRepository.findById(id);
        Floor floor = floor1.orElseThrow(() -> new EntityNotFoundException("Employee with id: " + id + " not found"));

        return floorToFloorResponseDto(floor);
    }

    @Override
    public FloorResponseDto create(FloorCreateDto floorCreateDto) {
        Floor floor = mapper.map(floorCreateDto, Floor.class);

        if (floorCreateDto.getOfficeId() != null) {
            Office office = officeRepository.findById(floorCreateDto.getOfficeId()).orElseThrow(
                    () -> new EntityNotFoundException("Office with id: " + floorCreateDto.getOfficeId() + " not found")
            );
            floor.setOffice(office);
        }

        return floorToFloorResponseDto(floorRepository.save(floor));
    }

    @Override
    public void deleteById(Long id) {
        floorRepository.deleteById(id);
        System.out.println(id);
    }


    @Override
    public FloorResponseDto update(Long id, FloorUpdateDto floorUpdateDto) {
        Floor floor = floorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Floor with id " + id + " not found"));
        mapper.map(floorUpdateDto, floor);
        return floorToFloorResponseDto(floorRepository.save(floor));
    }

    @Override
    public List<FloorResponseDto> getFloorsByOfId(Long id) {
        List<Floor> floorListByOfficeId = floorRepository.findFloorsByOfficeId(id);
        return floorListByOfficeId.stream().map(this::floorToFloorResponseDto).collect(Collectors.toList());
    }

    @Override
    public void setMap(Long id, MultipartHttpServletRequest request) {
        AttachmentResponseDto attachmentResponseDto = attachmentService.create(request);
        Attachment attachment = attachmentRepository.findById(attachmentResponseDto.getId()).get();
        Floor floor = floorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Floor with id " + id + " not found"));
        floor.setAttachment(attachment);
        floorRepository.save(floor);
    }

    @Override
    public void getMap(Long id, HttpServletResponse response) {
        Attachment attachmentByFloorId = attachmentRepository.findAttachmentByFloorId(id);
        attachmentService.getById(attachmentByFloorId.getId(), response);
    }

    private FloorResponseDto floorToFloorResponseDto(Floor floor) {
        FloorResponseDto map = mapper.map(floor, FloorResponseDto.class);
        if (floor.getAttachment() != null)
            map.setAttachmentResponseDto(mapper.map(floor.getAttachment(), AttachmentResponseDto.class));
        if (floor.getOffice() != null)
            map.setOfficeResponseDto(mapper.map(floor.getOffice(), OfficeResponseDto.class));
        return map;
    }
}
