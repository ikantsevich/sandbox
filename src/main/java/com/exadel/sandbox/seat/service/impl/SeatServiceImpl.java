package com.exadel.sandbox.seat.service.impl;

import com.exadel.sandbox.equipment.dto.EquipmentResponseDto;
import com.exadel.sandbox.equipment.entity.Equipment;
import com.exadel.sandbox.equipment.repository.EquipmentRepository;
import com.exadel.sandbox.exception.EntityNotFoundException;
import com.exadel.sandbox.seat.dto.SeatBaseDto;
import com.exadel.sandbox.seat.dto.SeatCreateDto;
import com.exadel.sandbox.seat.dto.SeatResponseDto;
import com.exadel.sandbox.seat.entity.Seat;
import com.exadel.sandbox.seat.repository.SeatRepository;
import com.exadel.sandbox.seat.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Transactional
public class SeatServiceImpl implements SeatService {
    private final SeatRepository seatRepository;
    private final EquipmentRepository equipmentRepository;
    private final ModelMapper mapper;

    @Override
    public List<SeatResponseDto> getAll() {
        List<Seat> seats = seatRepository.findAll();

        List<SeatResponseDto> seatResponseDtos = new ArrayList<>();

        for (Seat seat : seats) {
            seatResponseDtos.add(fullMap(seat));
        }


        return seatResponseDtos;
    }

    @Override
    public SeatResponseDto getById(Long id) {
        Optional<Seat> byId = seatRepository.findById(id);

        Seat seat = byId.orElseThrow(() -> new EntityNotFoundException("Seat not found"));

        return fullMap(seat);
    }

    @Override
    public SeatResponseDto create(SeatCreateDto seatCreateDto) {
        Seat seat = mapper.map(seatCreateDto, Seat.class);
        Set<Equipment> equipmentSet = seatCreateDto.getEquipmentBaseDtos()
                .stream()
                .map(equipmentBaseDto -> equipmentRepository.findByName(equipmentBaseDto.getName()))
                .collect(Collectors.toSet());
        seat.setEquipmentSet(equipmentSet);
        Seat savedSeat = seatRepository.save(seat);
        return fullMap(savedSeat);
    }

    @Override
    public void deleteById(Long id) {
        seatRepository.deleteById(id);
    }

    @Override
    public SeatResponseDto update(Long id, SeatBaseDto seatBaseDto) {
        Seat seat = seatRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Seat not found"));

        mapper.map(seatBaseDto, seat);

        return fullMap(seatRepository.save(seat));
    }

    private SeatResponseDto fullMap(Seat seat) {
        SeatResponseDto seatResponseDto = mapper.map(seat, SeatResponseDto.class);
        if (seat.getEquipmentSet() != null){
            Set<EquipmentResponseDto> equipments = seat.getEquipmentSet()
                    .stream()
                    .map(equipment -> mapper.map(equipment, EquipmentResponseDto.class))
                    .collect(Collectors.toSet());
            seatResponseDto.setEquipmentResponseDtos(equipments);
        }

        return seatResponseDto;
    }
}
