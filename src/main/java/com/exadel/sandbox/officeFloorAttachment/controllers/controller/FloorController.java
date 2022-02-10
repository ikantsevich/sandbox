package com.exadel.sandbox.officeFloorAttachment.controllers.controller;

import com.exadel.sandbox.officeFloorAttachment.controllers.dto.FloorDto;
import com.exadel.sandbox.officeFloorAttachment.controllers.service.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/floor")
public class FloorController {

    @Autowired
    FloorService floorService;

    @GetMapping("/list")
    List<FloorDto> getFloors() {
        return floorService.getFloors();
    }

    @GetMapping("/search")
    FloorDto getFloor(@RequestParam("id") Long id) {
        return floorService.getById(id);
    }

    @GetMapping("{id}")
    List<FloorDto> getFloorsByOfId(@PathVariable("id") Long id) {
        return floorService.getFloorsByOfId(id);
    }

    @PutMapping()
    FloorDto createFloor(@RequestBody FloorDto floorDto) {
        return floorService.create(floorDto);
    }

    @DeleteMapping("{id}")
    void deleteById(@PathVariable("id") Long id) {
        floorService.deleteById(id);
    }

    @PostMapping("{id}")
    FloorDto updateFloor(@PathVariable("id") Long id, @RequestBody FloorDto floorDto) {
        return floorService.update(id, floorDto);
    }
}
