package com.exadel.sandbox.parking_spot.controller;

import com.exadel.sandbox.parking_spot.dto.ParkingSpotCreateDto;
import com.exadel.sandbox.parking_spot.dto.ParkingSpotResponseDto;
import com.exadel.sandbox.parking_spot.dto.ParkingSpotUpdateDto;
import com.exadel.sandbox.parking_spot.service.ParkingSpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
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
    ResponseEntity<List<ParkingSpotResponseDto>> getByOfficeId(@PathVariable("id") Long id) {
        return parkingSpotService.findByOfficeId(id);
    }

    @PostMapping()
    ResponseEntity<ParkingSpotResponseDto> createParkingSpot(@Valid @RequestBody ParkingSpotCreateDto parkingSpotCreateDTO) {
        return parkingSpotService.create(parkingSpotCreateDTO);
    }

    @DeleteMapping("{id}")
    void deleteById(@PathVariable("id") Long id) {
        parkingSpotService.delete(id);
    }

    @PutMapping("{id}")
    ResponseEntity<ParkingSpotResponseDto> updateParkingSpot(@PathVariable("id") Long id,
                                                             @Valid @RequestBody ParkingSpotUpdateDto parkingSpotUpdateDTO) {
        return parkingSpotService.update(id, parkingSpotUpdateDTO);
    }

    @GetMapping("{id}/booked-dates")
    ResponseEntity<List<LocalDate>> getParkingSpotBookedDates(@PathVariable Long id) {
        return parkingSpotService.getParkingSpotBookedDates(id);
    }

}
