package com.exadel.sandbox.equipment.service;

import com.exadel.sandbox.equipment.dto.EquipmentBaseDto;
import com.exadel.sandbox.equipment.dto.EquipmentCreateDto;
import com.exadel.sandbox.equipment.dto.EquipmentResponseDto;
import com.exadel.sandbox.equipment.dto.EquipmentUpdateDto;

import java.util.List;

public interface EquipmentService {
    List<EquipmentResponseDto> getAll();

    EquipmentResponseDto getById(Long id);

    EquipmentResponseDto create(EquipmentCreateDto equipmentCreateDto);

    void deleteById(Long id);

    EquipmentResponseDto update(Long id, EquipmentUpdateDto equipmentUpdateDto);
}
