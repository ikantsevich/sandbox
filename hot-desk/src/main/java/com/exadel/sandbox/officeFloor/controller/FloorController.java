package com.exadel.sandbox.officeFloor.controller;

import com.exadel.sandbox.officeFloor.dto.floorDto.FloorCreateDto;
import com.exadel.sandbox.officeFloor.dto.floorDto.FloorResponseDto;
import com.exadel.sandbox.officeFloor.dto.floorDto.FloorUpdateDto;
import com.exadel.sandbox.officeFloor.service.FloorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/floor")
@RequiredArgsConstructor
public class FloorController {

    private final FloorService floorService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_MAPPER')")
    @GetMapping("/list")
    ResponseEntity<List<FloorResponseDto>> getFloors() {
        return floorService.getList();
    }

    @GetMapping("/offcie-id/{id}")
    ResponseEntity<FloorResponseDto> getById(@RequestParam("id") Long id) {
        return floorService.getById(id);
    }

    @GetMapping("{id}")
    ResponseEntity<List<FloorResponseDto>> getFloorById(@PathVariable("id") Long id) {
        return floorService.getFloorsByOfId(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MAPPER')")
    @PostMapping()
    ResponseEntity<FloorResponseDto> createFloor(@Valid @RequestBody FloorCreateDto floorCreateDto) {
        return floorService.create(floorCreateDto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MAPPER')")
    @DeleteMapping("{id}")
    void deleteById(@PathVariable Long id) {
        floorService.delete(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MAPPER')")
    @PutMapping("{id}")
    ResponseEntity<FloorResponseDto> updateFloor(@PathVariable("id") Long id, @Valid @RequestBody FloorUpdateDto floorUpdateDto) {
        return floorService.update(id, floorUpdateDto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MAPPER')")
    @PutMapping("{id}/set-map")
    void setMap(@PathVariable Long id, MultipartHttpServletRequest request) {
        floorService.setMap(id, request);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MAPPER')")
    @GetMapping("{id}/get-map")
    void getMap(@PathVariable Long id, HttpServletResponse response) {
        floorService.getMap(id, response);
    }
}
