package com.exadel.sandbox.parking.service;

import com.exadel.sandbox.parking.dto.ParkingCreateDto;
import com.exadel.sandbox.parking.dto.ParkingResponseDto;
import com.exadel.sandbox.parking.dto.ParkingUpdateDto;

import java.util.List;

public interface ParkingService {

    List<ParkingResponseDto> getParkings();

    ParkingResponseDto getParkingById(Long id);

    ParkingResponseDto create(ParkingCreateDto parkingCreateDto);

    void deleteById(Long id);

    ParkingResponseDto update(Long id, ParkingUpdateDto parkingUpdateDto);

}
