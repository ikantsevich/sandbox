package com.exadel.sandbox.seat.service;

import com.exadel.sandbox.seat.dto.SeatBaseDto;
import com.exadel.sandbox.seat.dto.SeatResponseDto;

import java.util.List;

public interface SeatService {
    List<SeatResponseDto> getAll();

    SeatResponseDto getById(Long id);

    SeatResponseDto create(SeatBaseDto seatBaseDto);

    void deleteById(Long id);

    SeatResponseDto update(Long id, SeatBaseDto employeeUpdateDto);
}
