package com.exadel.sandbox.parking.service;

import com.exadel.sandbox.parking.entity.Parking;
import com.exadel.sandbox.address.dto.AddressResponseDto;
import com.exadel.sandbox.address.entity.Address;
import com.exadel.sandbox.exception.EntityNotFoundException;
import com.exadel.sandbox.parking.dto.ParkingCreateDto;
import com.exadel.sandbox.parking.dto.ParkingResponseDto;
import com.exadel.sandbox.parking.dto.ParkingUpdateDto;
import com.exadel.sandbox.parking.repository.ParkingRepository;
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
public class ParkingServiceImpl implements ParkingService {

    private final ParkingRepository parkingRepository;
    private final ModelMapper mapper;

    @Override
    public List<ParkingResponseDto> getParkings() {
        List<Parking> parkings = parkingRepository.findAll();
        return parkings.stream()
                .map(this::fullMap)
                .collect(Collectors.toList());
    }

    @Override
    public ParkingResponseDto getParkingById(Long id) {
        Optional<Parking> parkingByID = parkingRepository.findById(id);
        Parking parking = parkingByID.orElseThrow(
                () -> new EntityNotFoundException("Can\'t get Parking with ID: " + id + ". Doesn\'t exist."));
        return fullMap(parking);
    }

    @Override
    public ParkingResponseDto create(ParkingCreateDto parkingCreateDto) {
        Parking parking = mapper.map(parkingCreateDto, Parking.class);

        if(parkingCreateDto.getAddressResponseDto() != null)
            parking.setAddress(mapper.map(parkingCreateDto.getAddressResponseDto(), Address.class));

        return fullMap(parkingRepository.save(parking));
    }

    @Override
    public void deleteById(Long id) {
        parkingRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can\'t delete Parking with ID: " + id + ". Doesn\'t exist."));
        parkingRepository.deleteById(id);
    }

    @Override
    public ParkingResponseDto update(Long id, ParkingUpdateDto parkingUpdateDto) {
        Parking parking = parkingRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can\'t update Parking with ID: " + id + ". Doesn\'t exist."));
        mapper.map(parkingUpdateDto, parking);
        return mapper.map(parkingRepository.save(parking), ParkingResponseDto.class);
    }

    private ParkingResponseDto fullMap(Parking parking) {
        ParkingResponseDto parkingResponseDto = mapper.map(parking, ParkingResponseDto.class);

        AddressResponseDto addressResponseDto = null;
        if (parking.getAddress() != null)
            addressResponseDto = mapper.map(parking.getAddress(), AddressResponseDto.class);

        parkingResponseDto.setAddressResponseDto(addressResponseDto);

        return parkingResponseDto;
    }
}
