package com.exadel.sandbox.officeFloorAttachment.controllers.service;

import com.exadel.sandbox.officeFloorAttachment.controllers.dto.FloorDto;
import org.springframework.stereotype.Component;

import java.util.List;


public interface FloorService {
    List<FloorDto> getFloors();

    FloorDto getById(Long id);

    FloorDto create(FloorDto floorDto);

    void deleteById(Long id);

    FloorDto update(Long id, FloorDto floorDto);

    List<FloorDto> getFloorsByOfId(Long id);
}
