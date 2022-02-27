package com.exadel.sandbox.seat.controller;

import com.exadel.sandbox.seat.service.SeatService;
import dtos.officeFloor.dto.floorDto.FloorResponseDto;
import dtos.seat.dto.SeatCreateDto;
import dtos.seat.dto.SeatResponseDto;
import dtos.seat.dto.SeatUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("seat")
@RequiredArgsConstructor
public class SeatController {
    private final SeatService seatService;

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


    @PostMapping()
    ResponseEntity<SeatResponseDto> createSeat(@RequestBody SeatCreateDto seatBaseDto) {
        return seatService.create(seatBaseDto);
    }

    @DeleteMapping("{id}")
    void deleteSeatById(@PathVariable Long id) {
        seatService.delete(id);
    }

    @PutMapping("{id}")
    ResponseEntity<SeatResponseDto> updateSeat(@PathVariable("id") Long id,
                                               @RequestBody SeatUpdateDto seatUpdateDto) {
        return seatService.update(id, seatUpdateDto);
    }
}
