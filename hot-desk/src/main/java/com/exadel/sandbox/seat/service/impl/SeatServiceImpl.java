package com.exadel.sandbox.seat.service.impl;

import com.exadel.sandbox.equipment.dto.EquipmentResponseDto;
import com.exadel.sandbox.equipment.repository.EquipmentRepository;
import com.exadel.sandbox.exception.EntityNotFoundException;
import com.exadel.sandbox.officeFloor.dto.floorDto.FloorResponseDto;
import com.exadel.sandbox.officeFloor.entities.Floor;
import com.exadel.sandbox.officeFloor.repositories.FloorRepository;
import com.exadel.sandbox.seat.dto.SeatCreateDto;
import com.exadel.sandbox.seat.dto.SeatResponseDto;
import com.exadel.sandbox.seat.dto.SeatUpdateDto;
import com.exadel.sandbox.seat.entity.Seat;
import com.exadel.sandbox.seat.repository.SeatRepository;
import com.exadel.sandbox.seat.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Transactional
public class SeatServiceImpl implements SeatService {
    private final SeatRepository seatRepository;
    private final EquipmentRepository equipmentRepository;
    private final FloorRepository floorRepository;
    private final ModelMapper mapper;

    @Override
    public List<SeatResponseDto> getAll() {
        List<Seat> seats = seatRepository.findAll();
        return seats.stream().map(this::seatToSeatResponseDto).collect(Collectors.toList());
    }

    @Override
    public SeatResponseDto getById(Long id) {
        Seat seat = seatRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Seat with id: " + id + "not found"));
        return seatToSeatResponseDto(seat);
    }

    @Override
    public SeatResponseDto create(SeatCreateDto seatCreateDto) {
        Seat seat = mapper.map(seatCreateDto, Seat.class);
        if (seatCreateDto.getFloorId() != null) {
            Floor floor = floorRepository.findById(seatCreateDto.getFloorId()).orElseThrow(
                    () -> new EntityNotFoundException("Floor with id " + seatCreateDto.getFloorId() + "nor found")
            );
            seat.setFloor(floor);
        }
        return seatToSeatResponseDto(seatRepository.save(seat));
    }

    @Override
    public void deleteById(Long id) {
        seatRepository.deleteById(id);
    }

    @Override
    public SeatResponseDto update(Long id, SeatUpdateDto seatUpdateDto) {
        Seat seat = seatRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Seat not found"));
        mapper.map(seatUpdateDto, seat);
        if (!seat.getFloor().getId().equals(seatUpdateDto.getFloorId())) {
            Floor floor = floorRepository.findById(seatUpdateDto.getFloorId()).orElseThrow(
                    () -> new EntityNotFoundException("Floor with id " + seatUpdateDto.getFloorId() + "nor found")
            );
            seat.setFloor(floor);
        }
        return seatToSeatResponseDto(seat);
    }

    @Override
    public List<SeatResponseDto> getSeatsByFloorId(Long floorId) {
        return seatRepository.findSeatsByFloorId(floorId).stream().map(this::seatToSeatResponseDto).collect(Collectors.toList());
    }


    private SeatResponseDto seatToSeatResponseDto(Seat seat) {
        SeatResponseDto map = mapper.map(seat, SeatResponseDto.class);
        if (seat.getEquipments() != null)
            map.setEquipmentResponseDtos(seat.getEquipments().stream().map(equipment -> mapper.map(equipment, EquipmentResponseDto.class)).collect(Collectors.toList()));
        if (seat.getFloor() != null)
            map.setFloorResponseDto(mapper.map(seat.getFloor(), FloorResponseDto.class));
        return map;
    }
}
