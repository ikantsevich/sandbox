package com.exadel.sandbox.equipment.service;

import com.exadel.sandbox.base.BaseCrudService;
import com.exadel.sandbox.equipment.entity.Equipment;
import com.exadel.sandbox.equipment.repository.EquipmentRepository;
import dtos.equipment.dto.EquipmentCreateDto;
import dtos.equipment.dto.EquipmentResponseDto;
import dtos.equipment.dto.EquipmentUpdateDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class EquipmentService extends BaseCrudService<Equipment, EquipmentResponseDto, EquipmentUpdateDto, EquipmentCreateDto, EquipmentRepository> {
    public EquipmentService(ModelMapper mapper, EquipmentRepository repository) {
        super(mapper, repository);
    }
}
