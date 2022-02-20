package com.exadel.sandbox.officeFloor.controller;

import com.exadel.sandbox.officeFloor.dto.floorDto.FloorCreateDto;
import com.exadel.sandbox.officeFloor.dto.floorDto.FloorResponseDto;
import com.exadel.sandbox.officeFloor.dto.floorDto.FloorUpdateDto;
import com.exadel.sandbox.officeFloor.service.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
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

    @GetMapping("/offcie-id/{id}")
    FloorResponseDto getById(@RequestParam("id") Long id) {
        return floorService.getById(id);
    }

    @GetMapping("{id}")
    List<FloorResponseDto> getFloorById(@PathVariable("id") Long id) {
        return floorService.getFloorsByOfId(id);
    }

    @PostMapping()
    FloorResponseDto createFloor(@RequestBody FloorCreateDto floorCreateDto) {
        return floorService.create(floorCreateDto);
    }

    @DeleteMapping("{id}")
    void deleteById(@PathVariable Long id) {
        floorService.deleteById(id);
    }

    @PutMapping("{id}")
    FloorResponseDto updateFloor(@PathVariable("id") Long id, @RequestBody FloorUpdateDto floorUpdateDto) {
        return floorService.update(id, floorUpdateDto);
    }

    @PutMapping("{id}/set-map")
    void setMap(@PathVariable Long id, MultipartHttpServletRequest request) {
        floorService.setMap(id, request);
    }

    @GetMapping("{id}/get-map")
    void getMap(@PathVariable Long id, HttpServletResponse response) {
        floorService.getMap(id, response);
    }
}
