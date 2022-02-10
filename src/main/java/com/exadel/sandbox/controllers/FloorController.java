package com.exadel.sandbox.controllers;

import com.exadel.sandbox.dto.FloorDto;
import com.exadel.sandbox.dto.OfficeDto;
import com.exadel.sandbox.entities.Floor;
import com.exadel.sandbox.entities.Office;
import com.exadel.sandbox.mapper.FloorMapper;
import com.exadel.sandbox.service.FloorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
