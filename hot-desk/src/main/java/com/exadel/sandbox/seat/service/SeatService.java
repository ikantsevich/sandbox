package com.exadel.sandbox.seat.service;

import com.exadel.sandbox.seat.dto.SeatBaseDto;
import com.exadel.sandbox.seat.dto.SeatCreateDto;
import com.exadel.sandbox.seat.dto.SeatResponseDto;
import com.exadel.sandbox.seat.dto.SeatUpdateDto;

import java.util.List;

public interface SeatService {
    List<SeatResponseDto> getAll();

    SeatResponseDto getById(Long id);

    SeatResponseDto create(SeatCreateDto seatCreateDto);

    void deleteById(Long id);

    SeatResponseDto update(Long id, SeatUpdateDto seatUpdateDto);
}
