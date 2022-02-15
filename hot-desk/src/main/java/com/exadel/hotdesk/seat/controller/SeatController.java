package com.exadel.sandbox.seat.controller;

import com.exadel.sandbox.seat.dto.SeatBaseDto;
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
    SeatResponseDto getById(@PathVariable("id") Long id) {

        return seatService.getById(id);
    }

    @PostMapping()
    SeatResponseDto createSeat(@RequestBody SeatCreateDto seatBaseDto) {

        return seatService.create(seatBaseDto);
    }

    @DeleteMapping("{id}")
    void deleteSeatById(@PathVariable("id") Long id) {
        seatService.deleteById(id);
    }

    @PutMapping("{id}")
    SeatResponseDto updateSeat(@PathVariable("id") Long id,
                               @RequestBody SeatUpdateDto seatBaseDto) {


        return seatService.update(id, seatBaseDto);
    }
}
