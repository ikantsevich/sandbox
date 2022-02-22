package com.exadel.sandbox.seat.controller;

import com.exadel.sandbox.seat.dto.SeatCreateDto;
import com.exadel.sandbox.seat.dto.SeatResponseDto;
import com.exadel.sandbox.seat.dto.SeatUpdateDto;
import com.exadel.sandbox.seat.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("seat")
@RequiredArgsConstructor
public class SeatController {
    private final SeatService seatService;

    @GetMapping("list")
    List<SeatResponseDto> getSeats() {
        return seatService.getAll();
    }

    @GetMapping("{id}")
    SeatResponseDto getById(@PathVariable Long id) {
        return seatService.getById(id);
    }

    @GetMapping("floor/{floorId}")
    List<SeatResponseDto> getSeatsByFloorId(@PathVariable Long floorId) {
        return seatService.getSeatsByFloorId(floorId);
    }


    @PostMapping()
    SeatResponseDto createSeat(@RequestBody SeatCreateDto seatBaseDto) {
        return seatService.create(seatBaseDto);
    }

    @DeleteMapping("{id}")
    void deleteSeatById(@PathVariable Long id) {
        seatService.deleteById(id);
    }

    @PutMapping("{id}")
    SeatResponseDto updateSeat(@PathVariable("id") Long id,
                               @RequestBody SeatUpdateDto seatUpdateDto) {
        return seatService.update(id, seatUpdateDto);
    }
}
