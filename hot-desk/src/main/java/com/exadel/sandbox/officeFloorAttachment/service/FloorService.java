package com.exadel.sandbox.officeFloorAttachment.service;

import com.exadel.sandbox.officeFloorAttachment.dto.floorDto.FloorCreateDto;
import com.exadel.sandbox.officeFloorAttachment.dto.floorDto.FloorResponseDto;
import com.exadel.sandbox.officeFloorAttachment.dto.floorDto.FloorUpdateDto;
import com.exadel.sandbox.officeFloorAttachment.repositories.FloorRepository;

import java.util.List;


public interface FloorService {
    List<FloorResponseDto> getFloors();

    FloorResponseDto getById(Long id);

    FloorResponseDto create(FloorCreateDto floorCreateDto);

    void deleteById(Long id);

    FloorResponseDto update(Long id, FloorUpdateDto floorUpdateDto);

    List<FloorResponseDto> getFloorsByOfId(Long id);
}
