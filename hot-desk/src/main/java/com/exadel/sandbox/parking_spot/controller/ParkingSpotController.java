package com.exadel.sandbox.parking_spot.controller;

import com.exadel.sandbox.parking_spot.dto.ParkingSpotCreateDto;
import com.exadel.sandbox.parking_spot.dto.ParkingSpotResponseDto;
import com.exadel.sandbox.parking_spot.dto.ParkingSpotUpdateDto;
import com.exadel.sandbox.parking_spot.service.ParkingSpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("parking-spot")
@RequiredArgsConstructor
public class ParkingSpotController {

    private final ParkingSpotService parkingSpotService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_MAPPER')")
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

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MAPPER')")
    @PostMapping()
    ResponseEntity<ParkingSpotResponseDto> createParkingSpot(@Valid @RequestBody ParkingSpotCreateDto parkingSpotCreateDTO) {
        return parkingSpotService.create(parkingSpotCreateDTO);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MAPPER')")
    @DeleteMapping("{id}")
    void deleteById(@PathVariable("id") Long id) {
        parkingSpotService.delete(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MAPPER')")
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
