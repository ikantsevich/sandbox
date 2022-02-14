package com.exadel.sandbox.equipment.service;

import com.exadel.sandbox.equipment.dto.EquipmentBaseDto;
import com.exadel.sandbox.equipment.dto.EquipmentResponseDto;

import java.util.List;

public interface EquipmentService {
    List<EquipmentResponseDto> getAll();

    EquipmentResponseDto getById(Long id);

    EquipmentResponseDto create(EquipmentBaseDto equipmentBaseDto);

    void deleteById(Long id);

    EquipmentResponseDto update(Long id, EquipmentBaseDto equipmentBaseDto);
}
