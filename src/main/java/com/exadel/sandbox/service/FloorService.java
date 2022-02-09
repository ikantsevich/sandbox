package com.exadel.sandbox.service;

import com.exadel.sandbox.dto.FloorDto;
import com.exadel.sandbox.entities.Floor;
import com.exadel.sandbox.entities.Office;

import java.util.List;

public interface FloorService {
    List<FloorDto> getFloors();

    FloorDto getById(Long id);

    FloorDto create(FloorDto floorDto);

    void deleteById(Long id);

    FloorDto update(Long id, FloorDto floorDto);

    List<FloorDto> getFloorsByOfId(Long id);
}
