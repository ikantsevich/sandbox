package com.exadel.sandbox.equipment.service;

import com.exadel.sandbox.base.BaseCrudService;
import com.exadel.sandbox.equipment.dto.EquipmentCreateDto;
import com.exadel.sandbox.equipment.dto.EquipmentResponseDto;
import com.exadel.sandbox.equipment.dto.EquipmentUpdateDto;
import com.exadel.sandbox.equipment.entity.Equipment;
import com.exadel.sandbox.equipment.repository.EquipmentRepository;
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
