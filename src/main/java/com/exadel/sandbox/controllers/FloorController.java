package com.exadel.sandbox.controllers;

import com.exadel.sandbox.dto.FloorDto;
import com.exadel.sandbox.dto.OfficeDto;
import com.exadel.sandbox.entities.Floor;
import com.exadel.sandbox.entities.Office;
import com.exadel.sandbox.mapper.FloorMapper;
import com.exadel.sandbox.service.FloorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("floor")
@RequiredArgsConstructor
public class FloorController {
    private FloorService floorService;
    private FloorMapper floorMapper;

    @GetMapping("list")
    List<FloorDto> getFloors(){
        List<Floor> floors = floorService.getFloors();
        return floors.stream().map(floorMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("search")
    FloorDto getFloor(@RequestParam("id") Long id){
        Floor floor = floorService.getById(id);
        return floorMapper.toDto(floor);
    }

    @GetMapping("{id}")
    List<FloorDto> getFloorsByOfId(@PathVariable("id") Long id){
        List<Floor> floors = floorService.getFloorsByOfId(id);
        return floors.stream().map(floorMapper::toDto).collect(Collectors.toList());
    }

    @PutMapping()
    FloorDto createFloor(@RequestBody FloorDto floorDto){
        Floor floor = floorMapper.toEntity(floorDto);
        floor = floorService.create(floor);
        return floorMapper.toDto(floor);
    }

    @DeleteMapping("{id}")
    void deleteById(@PathVariable("id") Long id){
        floorService.deleteById(id);
    }

    @PostMapping("{id}")
    FloorDto updateFloor(@PathVariable("id") Long id,
                           @RequestBody FloorDto floorDto ){
        Floor floor = floorMapper.toEntity(floorDto);
        floor = floorService.update(id, floor);
        return floorMapper.toDto(floor);
    }
}
