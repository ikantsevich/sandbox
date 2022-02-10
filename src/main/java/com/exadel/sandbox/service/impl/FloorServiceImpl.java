package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.FloorDto;
import com.exadel.sandbox.entities.Floor;
import com.exadel.sandbox.entities.Office;
import com.exadel.sandbox.exception.EntityNotFoundException;
import com.exadel.sandbox.mapper.FloorMapper;
import com.exadel.sandbox.repositories.FloorRepository;
import com.exadel.sandbox.service.FloorService;
import liquibase.pro.packaged.F;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class FloorServiceImpl implements FloorService {

    @Autowired
    FloorRepository floorRepository;
    @Autowired
    FloorMapper floorMapper;

    @Override
    public List<FloorDto> getFloors() {
        List<Floor> all = floorRepository.findAll();
        List<FloorDto> list = new ArrayList<>();
        for (Floor floor: all){
            list.add(floorMapper.toDto(floor));
        }
        return list;
    }

    @Override
    public FloorDto getById(Long id) {
        Optional<Floor> one = floorRepository.findById(id);
        Floor floor = one.orElseThrow(() -> new EntityNotFoundException("Employee with id: " + id + " not found"));
        return floorMapper.toDto(floor);
    }

    @Override
    public FloorDto create(FloorDto floorDto) {
        Floor floor = floorMapper.toEntity(floorDto);
        Floor save = floorRepository.save(floor);
        return floorMapper.toDto(save);
    }

    @Override
    public void deleteById(Long id) {
        floorRepository.deleteById(id);
    }


    @Override
    public FloorDto update(Long id, FloorDto floorDto) {
        Floor floor = floorMapper.toEntity(floorDto);
        FloorDto byId = getById(id);
        if (byId == null)
            throw new RuntimeException("Floor not found");

        floor.setId(id);
        Floor save = floorRepository.save(floor);
        return floorMapper.toDto(save);
    }

    @Override
    public List<FloorDto> getFloorsByOfId(Long id) {
        List<Floor> allByOfId = floorRepository.getAllByOfId(id);
        List<FloorDto> list = new ArrayList<>();
        for (Floor floor: allByOfId){
            list.add(floorMapper.toDto(floor));
        }
        return list;
    }
}
