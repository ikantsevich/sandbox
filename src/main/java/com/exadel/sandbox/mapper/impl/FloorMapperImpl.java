package com.exadel.sandbox.mapper.impl;

import com.exadel.sandbox.dto.FloorDto;
import com.exadel.sandbox.entities.Floor;
import com.exadel.sandbox.mapper.FloorMapper;

public class FloorMapperImpl implements FloorMapper {
    @Override
    public Floor toEntity(FloorDto floorDto) {
        return new Floor(
                floorDto.getFlId(),
                floorDto.getOfId(),
                floorDto.getAtId(),
                floorDto.getFlNum(),
                floorDto.getKitchenStatus(),
                floorDto.getMeetingRoomStatus(),
                floorDto.getFlCreated(),
                floorDto.getFlModified()
        );
    }

    @Override
    public FloorDto toDto(Floor floor) {
        return new FloorDto(
                floor.getId(),
                floor.getOfId(),
                floor.getAtId(),
                floor.getFlNum(),
                floor.getKitchenStatus(),
                floor.getMeetingRoomStatus(),
                floor.getFlCreated(),
                floor.getFlModified()
        );
    }
}
