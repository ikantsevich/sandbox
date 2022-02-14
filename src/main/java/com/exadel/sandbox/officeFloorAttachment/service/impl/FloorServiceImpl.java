package com.exadel.sandbox.officeFloorAttachment.service.impl;

import com.exadel.sandbox.officeFloorAttachment.dto.floorDto.FloorCreateDto;
import com.exadel.sandbox.officeFloorAttachment.dto.floorDto.FloorResponseDto;
import com.exadel.sandbox.officeFloorAttachment.dto.floorDto.FloorUpdateDto;
import com.exadel.sandbox.officeFloorAttachment.entities.Floor;
import com.exadel.sandbox.exception.EntityNotFoundException;
import com.exadel.sandbox.officeFloorAttachment.repositories.FloorRepository;
import com.exadel.sandbox.officeFloorAttachment.service.FloorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FloorServiceImpl implements FloorService {

    @Autowired
    FloorRepository floorRepository;

    private final ModelMapper mapper;

    @Override
    public List<FloorResponseDto> getFloors() {
        List<Floor> floorList = floorRepository.findAll();
        return floorList.stream().map(floor -> mapper.map(floor, FloorResponseDto.class)).collect(Collectors.toList());
    }

    @Override
    public FloorResponseDto getById(Long id) {
        Optional<Floor> floor1 = floorRepository.findById(id);
        Floor floor = floor1.orElseThrow(() -> new EntityNotFoundException("Employee with id: " + id + " not found"));
        return mapper.map(floor, FloorResponseDto.class);
    }

    @Override
    public FloorResponseDto create(FloorCreateDto floorCreateDto) {
        Floor floor = mapper.map(floorCreateDto, Floor.class);
        Floor savedFloor = floorRepository.save(floor);
        return mapper.map(savedFloor, FloorResponseDto.class);
    }

    @Override
    public void deleteById(Long id) {
        floorRepository.deleteById(id);
    }


    @Override
    public FloorResponseDto update(Long id, FloorUpdateDto floorUpdateDto) {
        Floor floor = mapper. map(floorUpdateDto, Floor.class);
        FloorResponseDto floorResponseDto = getById(id);
        if (floorResponseDto == null)
            throw new EntityNotFoundException("Floor not found with this " + id);

        floor.setId(id);
        Floor savedFloor = floorRepository.save(floor);
        return mapper.map(savedFloor, FloorResponseDto.class);
    }

    @Override
    public List<FloorResponseDto> getFloorsByOfId(Long id) {
        List<Floor> floorListByOfficeId = floorRepository.getAllByOfId(id);
        return floorListByOfficeId.stream().map(floor -> mapper.map(floor, FloorResponseDto.class)).collect(Collectors.toList());
    }
}
