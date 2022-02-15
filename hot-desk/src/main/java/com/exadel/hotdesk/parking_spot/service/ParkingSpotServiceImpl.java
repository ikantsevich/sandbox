package com.exadel.hotdesk.parking_spot.service;

import com.exadel.hotdesk.exception.EntityNotFoundException;
import com.exadel.hotdesk.parking.dto.ParkingResponseDto;
import com.exadel.hotdesk.parking.entity.Parking;
import com.exadel.hotdesk.parking_spot.dto.ParkingSpotCreateDto;
import com.exadel.hotdesk.parking_spot.dto.ParkingSpotResponseDto;
import com.exadel.hotdesk.parking_spot.dto.ParkingSpotUpdateDto;
import com.exadel.hotdesk.parking_spot.entity.ParkingSpot;
import com.exadel.hotdesk.parking_spot.repository.ParkingSpotRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ParkingSpotServiceImpl implements ParkingSpotService {

    private final ParkingSpotRepository parkingSpotRepository;
    private final ModelMapper mapper;

    @Override
    public List<ParkingSpotResponseDto> getParkingSpots() {
        List<ParkingSpot> parkingSpots = parkingSpotRepository.findAll();
        return parkingSpots.stream()
                .map(this::fullMap)
                .collect(Collectors.toList());
    }

    @Override
    public ParkingSpotResponseDto getParkingSpotById(Long id) {
        Optional<ParkingSpot> parkingSpotByID = parkingSpotRepository.findById(id);
        ParkingSpot parkingSpot = parkingSpotByID.orElseThrow(
                () -> new EntityNotFoundException("Can\'t get Parking Spot with ID: " + id + ". Doesn\'t exist."));
        return fullMap(parkingSpot);
    }

    @Override
    public ParkingSpotResponseDto create(ParkingSpotCreateDto parkingSpotCreateDto) {
        ParkingSpot parkingSpot = mapper.map(parkingSpotCreateDto, ParkingSpot.class);

        if(parkingSpotCreateDto.getParkingResponseDto() != null)
            parkingSpot.setParking(mapper.map(parkingSpotCreateDto.getParkingResponseDto(), Parking.class));

        return fullMap(parkingSpotRepository.save(parkingSpot));
    }

    @Override
    public void deleteById(Long id) {
        parkingSpotRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can\'t delete Parking Spot with ID: " + id + ". Doesn\'t exist."));
        parkingSpotRepository.deleteById(id);
    }

    @Override
    public ParkingSpotResponseDto update(Long id, ParkingSpotUpdateDto parkingSpotUpdateDto) {
        ParkingSpot parkingSpot = parkingSpotRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can\'t update Parking Spot with ID: " + id + ". Doesn\'t exist."));
        mapper.map(parkingSpotUpdateDto, parkingSpot);
        return mapper.map(parkingSpotRepository.save(parkingSpot), ParkingSpotResponseDto.class);
    }

    private ParkingSpotResponseDto fullMap(ParkingSpot parkingSpot) {
        ParkingSpotResponseDto parkingSpotResponseDto = mapper.map(parkingSpot, ParkingSpotResponseDto.class);

        ParkingResponseDto parkingResponseDto = null;
        if (parkingSpot.getParking() != null)
            parkingResponseDto = mapper.map(parkingSpot.getParking(), ParkingResponseDto.class);

        parkingSpotResponseDto.setParkingResponseDto(parkingResponseDto);

        return parkingSpotResponseDto;
    }
}
