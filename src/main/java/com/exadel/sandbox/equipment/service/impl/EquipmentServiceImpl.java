package com.exadel.sandbox.equipment.service.impl;

import com.exadel.sandbox.equipment.dto.EquipmentBaseDto;
import com.exadel.sandbox.equipment.dto.EquipmentResponseDto;
import com.exadel.sandbox.equipment.entity.Equipment;
import com.exadel.sandbox.equipment.repository.EquipmentRepository;
import com.exadel.sandbox.equipment.service.EquipmentService;
import com.exadel.sandbox.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Transactional
public class EquipmentServiceImpl implements EquipmentService {
    private final ModelMapper mapper;
    private final EquipmentRepository equipmentRepository;

    @Override
    public List<EquipmentResponseDto> getAll() {
        List<Equipment> equipments = equipmentRepository.findAll();

        return equipments.stream().map(equipment -> mapper.map(equipment, EquipmentResponseDto.class)).collect(Collectors.toList());
    }

    @Override
    public EquipmentResponseDto getById(Long id) {
        Optional<Equipment> byId = equipmentRepository.findById(id);

        Equipment equipment = byId.orElseThrow(() -> new EntityNotFoundException("Equipment not found"));


        return mapper.map(equipment, EquipmentResponseDto.class);
    }

    @Override
    public EquipmentResponseDto create(EquipmentBaseDto equipmentBaseDto) {
        Equipment equipment = mapper.map(equipmentBaseDto, Equipment.class);

        return mapper.map(equipmentRepository.save(equipment), EquipmentResponseDto.class);
    }

    @Override
    public void deleteById(Long id) {
        equipmentRepository.deleteById(id);
    }

    @Override
    public EquipmentResponseDto update(Long id, EquipmentBaseDto equipmentBaseDto) {
        Equipment equipment = equipmentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Equipment not found"));

        mapper.map(equipmentBaseDto, equipment);

        return mapper.map(equipmentRepository.save(equipment), EquipmentResponseDto.class);
    }
}
