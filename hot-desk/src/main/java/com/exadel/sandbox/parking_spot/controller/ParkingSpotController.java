package com.exadel.sandbox.parking_spot.controller;

import com.exadel.sandbox.parking_spot.service.ParkingSpotService;
import dtos.parkingSpot.dto.ParkingSpotCreateDto;
import dtos.parkingSpot.dto.ParkingSpotResponseDto;
import dtos.parkingSpot.dto.ParkingSpotUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("parking-spot")
@RequiredArgsConstructor
public class ParkingSpotController {

    private final ParkingSpotService parkingSpotService;


    @GetMapping("list")
    ResponseEntity<List<ParkingSpotResponseDto>> getParkingSpots() {
        return parkingSpotService.getList();
    }

    @GetMapping("{id}")
    ResponseEntity<ParkingSpotResponseDto> getParkingSpotById(@PathVariable("id") Long id) {
        return parkingSpotService.getById(id);
    }

    @GetMapping("office-id/{id}")
    ResponseEntity<ParkingSpotResponseDto> getByOfficeId(@PathVariable("id") Long id) {
        return parkingSpotService.findByOfficeId(id);
    }

    @PostMapping()
    ResponseEntity<ParkingSpotResponseDto> createParkingSpot(@RequestBody ParkingSpotCreateDto parkingSpotCreateDTO) {
        return parkingSpotService.create(parkingSpotCreateDTO);
    }

    @DeleteMapping("{id}")
    void deleteById(@PathVariable("id") Long id) {
        parkingSpotService.delete(id);
    }

    @PutMapping("{id}")
    ResponseEntity<ParkingSpotResponseDto> updateParkingSpot(@PathVariable("id") Long id,
                                                             @RequestBody ParkingSpotUpdateDto parkingSpotUpdateDTO) {
        return parkingSpotService.update(id, parkingSpotUpdateDTO);
    }

}
