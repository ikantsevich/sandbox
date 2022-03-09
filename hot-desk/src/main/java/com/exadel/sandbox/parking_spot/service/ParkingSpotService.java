package com.exadel.sandbox.parking_spot.service;

import com.exadel.sandbox.base.BaseCrudService;
import com.exadel.sandbox.parking_spot.dto.ParkingSpotCreateDto;
import com.exadel.sandbox.parking_spot.dto.ParkingSpotResponseDto;
import com.exadel.sandbox.parking_spot.dto.ParkingSpotUpdateDto;
import com.exadel.sandbox.parking_spot.entity.ParkingSpot;
import com.exadel.sandbox.parking_spot.repository.ParkingSpotRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class ParkingSpotService extends BaseCrudService<ParkingSpot, ParkingSpotResponseDto, ParkingSpotUpdateDto, ParkingSpotCreateDto, ParkingSpotRepository> {
    private final ParkingSpotRepository parkingSpotRepository;

    public ParkingSpotService(ModelMapper mapper, ParkingSpotRepository repository, ParkingSpotRepository parkingSpotRepository) {
        super(mapper, repository);
        this.parkingSpotRepository = parkingSpotRepository;
    }

    public ResponseEntity<List<ParkingSpotResponseDto>> findByOfficeId(Long id) {
        final List<ParkingSpot> parkingSpotByOfficeId = parkingSpotRepository.findParkingSpotByOfficeId(id);
        return ResponseEntity.ok(parkingSpotByOfficeId.stream().map(parkingSpot -> mapper.map(parkingSpot, ParkingSpotResponseDto.class)).collect(Collectors.toList()));
    }

    public ResponseEntity<List<LocalDate>> getParkingSpotBookedDates(Long id) {
        List<LocalDate> bookedDatesBySpotId = repository.findBookedDatesBySpotId(id);
        return ResponseEntity.ok(bookedDatesBySpotId);
    }
}
