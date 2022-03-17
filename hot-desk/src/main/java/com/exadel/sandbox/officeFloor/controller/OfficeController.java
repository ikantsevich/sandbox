package com.exadel.sandbox.officeFloor.controller;

import com.exadel.sandbox.officeFloor.dto.officeDto.OfficeCreateDto;
import com.exadel.sandbox.officeFloor.dto.officeDto.OfficeResponseDto;
import com.exadel.sandbox.officeFloor.dto.officeDto.OfficeUpdateDto;
import com.exadel.sandbox.officeFloor.service.OfficeService;
import com.exadel.sandbox.parking_spot.dto.ParkingSpotResponseDto;
import com.exadel.sandbox.seat.dto.SeatResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/office")
@RequiredArgsConstructor
public class OfficeController {

    private final OfficeService officeService;

    @GetMapping("/list")
    ResponseEntity<List<OfficeResponseDto>> getOffices() {
        return officeService.getList();
    }

    @GetMapping("{id}")
    ResponseEntity<OfficeResponseDto> getOffice(@PathVariable Long id) {
        return officeService.getById(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MAPPER')")
    @PostMapping()
    ResponseEntity<OfficeResponseDto> createOffice(@Valid @RequestBody OfficeCreateDto officeCreateDto) {
        return officeService.create(officeCreateDto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MAPPER')")
    @DeleteMapping("{id}")
    void deleteById(@PathVariable("id") Long id) {
        officeService.delete(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MAPPER')")
    @PutMapping("{id}")
    ResponseEntity<OfficeResponseDto> updateOffice(@PathVariable("id") Long id, @Valid @RequestBody OfficeUpdateDto officeUpdateDto) {
        return officeService.update(id, officeUpdateDto);
    }

    @PutMapping("{id}/free-seats")
    ResponseEntity<List<SeatResponseDto>> getFreeSeats(@PathVariable Long id, @RequestBody List<LocalDate> dates) {
        return officeService.getFreeSeats(id, dates);
    }

    @PutMapping("{id}/free-spots")
    ResponseEntity<List<ParkingSpotResponseDto>> getFreeSpots(@PathVariable Long id, @RequestBody List<LocalDate> dates){
        return officeService.getFreeSpots(id, dates);
    }

    @GetMapping("/address/{id}")
    ResponseEntity<OfficeResponseDto> getOfficeByAddressId(@PathVariable Long id){
        return officeService.getOfficeByAddressId(id);
    }
}
