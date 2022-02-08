package com.exadel.sandbox.service;

import com.exadel.sandbox.entities.Floor;
import com.exadel.sandbox.entities.Office;

import java.util.List;

public interface FloorService {
    List<Floor> getFloors();

    Floor getById(Long id);

    Floor create(Floor floor);

    void deleteById(Long id);

    Floor update(Long id, Floor floor);

    List<Floor> getFloorsByOfId(Long id);
}
