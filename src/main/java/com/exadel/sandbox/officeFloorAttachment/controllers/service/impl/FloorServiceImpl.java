package com.exadel.sandbox.officeFloorAttachment.controllers.service.impl;

import com.exadel.sandbox.officeFloorAttachment.controllers.dto.FloorDto;
import com.exadel.sandbox.officeFloorAttachment.controllers.entities.Floor;
import com.exadel.sandbox.officeFloorAttachment.controllers.exception.EntityNotFoundException;
import com.exadel.sandbox.officeFloorAttachment.controllers.repositories.FloorRepository;
import com.exadel.sandbox.officeFloorAttachment.controllers.service.FloorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FloorServiceImpl implements FloorService {

    @Autowired
    FloorRepository floorRepository;

    private final ModelMapper mapper;

    @Override
    public List<FloorDto> getFloors() {
        List<Floor> all = floorRepository.findAll();
        List<FloorDto> list = new ArrayList<>();
        for (Floor floor: all){
            list.add(mapper.map(floor, FloorDto.class));
        }
        return list;
    }

    @Override
    public FloorDto getById(Long id) {
        Optional<Floor> one = floorRepository.findById(id);
        Floor floor = one.orElseThrow(() -> new EntityNotFoundException("Employee with id: " + id + " not found"));
        return mapper.map(floor, FloorDto.class);
    }

    @Override
    public FloorDto create(FloorDto floorDto) {
        Floor floor = mapper.map(floorDto, Floor.class);
        Floor save = floorRepository.save(floor);
        return mapper.map(save, FloorDto.class);
    }

    @Override
    public void deleteById(Long id) {
        floorRepository.deleteById(id);
    }


    @Override
    public FloorDto update(Long id, FloorDto floorDto) {
        Floor floor = mapper. map(floorDto, Floor.class);
        FloorDto byId = getById(id);
        if (byId == null)
            throw new RuntimeException("Floor not found");

        floor.setId(id);
        Floor save = floorRepository.save(floor);
        return mapper.map(save, FloorDto.class);
    }

    @Override
    public List<FloorDto> getFloorsByOfId(Long id) {
        List<Floor> allByOfId = floorRepository.getAllByOfId(id);
        List<FloorDto> list = new ArrayList<>();
        for (Floor floor: allByOfId){
            list.add(mapper.map(floor, FloorDto.class));
        }
        return list;
    }
}
