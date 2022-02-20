package com.exadel.sandbox.parking_spot.service;

import com.exadel.sandbox.exception.EntityNotFoundException;
import com.exadel.sandbox.officeFloor.dto.officeDto.OfficeResponseDto;
import com.exadel.sandbox.officeFloor.repositories.OfficeRepository;
import com.exadel.sandbox.parking_spot.dto.ParkingSpotCreateDto;
import com.exadel.sandbox.parking_spot.dto.ParkingSpotResponseDto;
import com.exadel.sandbox.parking_spot.dto.ParkingSpotUpdateDto;
import com.exadel.sandbox.parking_spot.entity.ParkingSpot;
import com.exadel.sandbox.parking_spot.repository.ParkingSpotRepository;
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
    private final OfficeRepository officeRepository;
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
        parkingSpot.setOffice(officeRepository.findById(parkingSpotCreateDto.getOfficeId()).orElseThrow(
                () -> new EntityNotFoundException("Office with id: " + parkingSpotCreateDto.getOfficeId() + " not found")
        ));

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

    @Override
    public ParkingSpotResponseDto findByOfficeId(Long id) {
        ParkingSpot parkingSpotByOfficeId = parkingSpotRepository.findParkingSpotByOfficeId(id);
        return fullMap(parkingSpotByOfficeId);
    }

    ParkingSpotResponseDto fullMap(ParkingSpot parkingSpot) {
        ParkingSpotResponseDto responseDto = mapper.map(parkingSpot, ParkingSpotResponseDto.class);
        if (parkingSpot.getOffice() != null)
            responseDto.setOfficeResponseDto(mapper.map(parkingSpot.getOffice(), OfficeResponseDto.class));
        return responseDto;
    }
}
