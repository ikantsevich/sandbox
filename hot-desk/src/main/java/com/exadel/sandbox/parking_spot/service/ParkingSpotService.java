package com.exadel.sandbox.parking_spot.service;

import com.exadel.sandbox.base.BaseCrudService;
import com.exadel.sandbox.parking_spot.dto.ParkingSpotCreateDto;
import com.exadel.sandbox.parking_spot.dto.ParkingSpotResponseDto;
import com.exadel.sandbox.parking_spot.dto.ParkingSpotUpdateDto;
import com.exadel.sandbox.parking_spot.entity.ParkingSpot;
import com.exadel.sandbox.parking_spot.repository.ParkingSpotRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ParkingSpotService extends BaseCrudService<ParkingSpot, ParkingSpotResponseDto, ParkingSpotUpdateDto, ParkingSpotCreateDto, ParkingSpotRepository> {
    private final ParkingSpotRepository parkingSpotRepository;

    public ParkingSpotService(ModelMapper mapper, ParkingSpotRepository repository, ParkingSpotRepository parkingSpotRepository) {
        super(mapper, repository);
        this.parkingSpotRepository = parkingSpotRepository;
    }

    public ResponseEntity<ParkingSpotResponseDto> findByOfficeId(Long id) {
        ParkingSpot parkingSpot = parkingSpotRepository.findParkingSpotByOfficeId(id);
        return ResponseEntity.ok(mapper.map(parkingSpot, ParkingSpotResponseDto.class));
    }
}
