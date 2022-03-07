package com.exadel.sandbox.seat.controller;

import com.exadel.sandbox.officeFloor.dto.floorDto.FloorResponseDto;
import com.exadel.sandbox.seat.dto.SeatCreateDto;
import com.exadel.sandbox.seat.dto.SeatResponseDto;
import com.exadel.sandbox.seat.dto.SeatUpdateDto;
import com.exadel.sandbox.seat.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("seat")
@RequiredArgsConstructor
public class SeatController {
    private final SeatService seatService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_MAPPER')")
    @GetMapping("list")
    ResponseEntity<List<SeatResponseDto>> getSeats() {
        return seatService.getList();
    }

    @GetMapping("{id}")
    ResponseEntity<SeatResponseDto> getById(@PathVariable Long id) {
        return seatService.getById(id);
    }

    @GetMapping("floor/{floorId}")
    ResponseEntity<List<FloorResponseDto>> getSeatsByFloorId(@PathVariable Long floorId) {
        return seatService.getSeatsByFloorId(floorId);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MAPPER')")
    @PostMapping()
    ResponseEntity<SeatResponseDto> createSeat(@Valid @RequestBody SeatCreateDto seatBaseDto) {
        return seatService.create(seatBaseDto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MAPPER')")
    @DeleteMapping("{id}")
    void deleteSeatById(@PathVariable Long id) {
        seatService.delete(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MAPPER')")
    @PutMapping("{id}")
    ResponseEntity<SeatResponseDto> updateSeat(@PathVariable("id") Long id,
                                               @Valid @RequestBody SeatUpdateDto seatUpdateDto) {
        return seatService.update(id, seatUpdateDto);
    }

    @GetMapping("{id}/booked-dates")
    ResponseEntity<List<LocalDate>> getSeatsBookedDates(@PathVariable Long id) {
        return seatService.getSeatsBookedDatesById(id);
    }
}
