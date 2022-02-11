package com.exadel.sandbox.officeFloorAttachment.controller;

import com.exadel.sandbox.officeFloorAttachment.dto.floorDto.FloorCreateDto;
import com.exadel.sandbox.officeFloorAttachment.dto.floorDto.FloorResponseDto;
import com.exadel.sandbox.officeFloorAttachment.dto.floorDto.FloorUpdateDto;
import com.exadel.sandbox.officeFloorAttachment.service.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/floor")
public class FloorController {

    @Autowired
    FloorService floorService;

    @GetMapping("/list")
    List<FloorResponseDto> getFloors() {
        return floorService.getFloors();
    }

    @GetMapping("/search")
    FloorResponseDto getFloor(@RequestParam("id") Long id) {
        return floorService.getById(id);
    }

    @GetMapping("{id}")
    List<FloorResponseDto> getFloorsByOfId(@PathVariable("id") Long id) {
        return floorService.getFloorsByOfId(id);
    }

    @PostMapping()
    FloorResponseDto createFloor(@RequestBody FloorCreateDto floorCreateDto) {
        return floorService.create(floorCreateDto);
    }

    @DeleteMapping("{id}")
    void deleteById(@PathVariable("id") Long id) {
        floorService.deleteById(id);
    }

    @PutMapping("{id}")
    FloorResponseDto updateFloor(@PathVariable("id") Long id, @RequestBody FloorUpdateDto floorUpdateDto) {
        return floorService.update(id, floorUpdateDto);
    }
}
