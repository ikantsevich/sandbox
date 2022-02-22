package com.exadel.sandbox.equipment.service.impl;

import com.exadel.sandbox.equipment.dto.EquipmentCreateDto;
import com.exadel.sandbox.equipment.dto.EquipmentResponseDto;
import com.exadel.sandbox.equipment.dto.EquipmentUpdateDto;
import com.exadel.sandbox.equipment.entity.Equipment;
import com.exadel.sandbox.equipment.repository.EquipmentRepository;
import com.exadel.sandbox.equipment.service.EquipmentService;
import com.exadel.sandbox.exception.EntityNotFoundException;
import com.exadel.sandbox.seat.entity.Seat;
import com.exadel.sandbox.seat.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Transactional
public class EquipmentServiceImpl implements EquipmentService {
    private final ModelMapper mapper;
    private final EquipmentRepository equipmentRepository;
    private final SeatRepository seatRepository;

    @Override
    public List<EquipmentResponseDto> getAll() {
        List<Equipment> equipments = equipmentRepository.findAll();

        return equipments.stream().map(this::equipmentToResponse).collect(Collectors.toList());
    }

    @Override
    public EquipmentResponseDto getById(Long id) {
        Equipment equipment = equipmentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Equipment not found"));

        return equipmentToResponse(equipment);
    }

    @Override
    public EquipmentResponseDto create(EquipmentCreateDto equipmentCreateDto) {
        Equipment equipment = mapper.map(equipmentCreateDto, Equipment.class);

        Seat seat = seatRepository.findById(equipmentCreateDto.getSeatId()).orElseThrow(
                () -> new EntityNotFoundException("Seat with id: " + equipmentCreateDto.getSeatId() + " not found")
        );
        equipment.setSeat(seat);

        return equipmentToResponse(equipmentRepository.save(equipment));
    }

    @Override
    public void deleteById(Long id) {
        equipmentRepository.deleteById(id);
    }

    @Override
    public EquipmentResponseDto update(Long id, EquipmentUpdateDto equipmentUpdateDto) {
        Equipment equipment = equipmentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Equipment not found"));

        mapper.map(equipmentUpdateDto, equipment);
        Seat seat = seatRepository.findById(equipmentUpdateDto.getSeatId()).orElseThrow(
                () -> new EntityNotFoundException("Seat with id: " + equipmentUpdateDto.getSeatId() + " not found")
        );
        equipment.setSeat(seat);

        return equipmentToResponse(equipment);
    }

    EquipmentResponseDto equipmentToResponse(Equipment equipment) {
        EquipmentResponseDto map = mapper.map(equipment, EquipmentResponseDto.class);
        map.setSeatId(equipment.getSeat().getId());
        return map;
    }
}
