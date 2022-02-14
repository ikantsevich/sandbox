package com.exadel.sandbox.parking_spot.service;

import com.exadel.sandbox.parking_spot.dto.ParkingSpotCreateDto;
import com.exadel.sandbox.parking_spot.dto.ParkingSpotResponseDto;
import com.exadel.sandbox.parking_spot.dto.ParkingSpotUpdateDto;

import java.util.List;

public interface ParkingSpotService {

    List<ParkingSpotResponseDto> getParkingSpots();

    ParkingSpotResponseDto getParkingSpotById(Long id);

    ParkingSpotResponseDto create(ParkingSpotCreateDto parkingSpotCreateDto);

    void deleteById(Long id);

    ParkingSpotResponseDto update(Long id, ParkingSpotUpdateDto parkingSpotUpdateDto);

}
