package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.entities.Floor;
import com.exadel.sandbox.entities.Office;
import com.exadel.sandbox.repositories.FloorRepository;
import com.exadel.sandbox.service.FloorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FloorServiceImpl implements FloorService {

    private final FloorRepository floorRepository;

    @Override
    public List<Floor> getFloors() {
        return floorRepository.findAll();
    }

    @Override
    public Floor getById(Long id) {
        return getById(id);
    }

    @Override
    public Floor create(Floor floor) {
        return floorRepository.save(floor);
    }

    @Override
    public void deleteById(Long id) {
        floorRepository.deleteById(id);
    }

    @Override
    public Floor update(Long id, Floor floor) {
        Floor byId = getById(id);
        if (byId == null)
            throw new RuntimeException("Floor not found");

        floor.setId(id);
        return floorRepository.save(floor);
    }

    @Override
    public List<Floor> getFloorsByOfId(Long id) {
        return floorRepository.getAllByOfId(id);
    }
}
