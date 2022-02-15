package com.exadel.hotdesk.parking_spot.service;

import com.exadel.hotdesk.parking_spot.dto.ParkingSpotCreateDto;
import com.exadel.hotdesk.parking_spot.dto.ParkingSpotResponseDto;
import com.exadel.hotdesk.parking_spot.dto.ParkingSpotUpdateDto;

import java.util.List;

public interface ParkingSpotService {

    List<ParkingSpotResponseDto> getParkingSpots();

    ParkingSpotResponseDto getParkingSpotById(Long id);

    ParkingSpotResponseDto create(ParkingSpotCreateDto parkingSpotCreateDto);

    void deleteById(Long id);

    ParkingSpotResponseDto update(Long id, ParkingSpotUpdateDto parkingSpotUpdateDto);

}
