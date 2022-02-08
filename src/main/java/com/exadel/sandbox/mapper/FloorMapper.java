package com.exadel.sandbox.mapper;

import com.exadel.sandbox.dto.FloorDto;
import com.exadel.sandbox.entities.Floor;

public interface FloorMapper {
    Floor toEntity(FloorDto floorDto);
    FloorDto toDto(Floor floor);
}
