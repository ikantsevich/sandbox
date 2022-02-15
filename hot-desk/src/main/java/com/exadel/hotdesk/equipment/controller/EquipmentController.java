package com.exadel.sandbox.equipment.controller;

import com.exadel.sandbox.equipment.dto.EquipmentBaseDto;
import com.exadel.sandbox.equipment.dto.EquipmentCreateDto;
import com.exadel.sandbox.equipment.dto.EquipmentResponseDto;
import com.exadel.sandbox.equipment.dto.EquipmentUpdateDto;
import com.exadel.sandbox.equipment.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("equipment")
@RequiredArgsConstructor
public class EquipmentController {
    private final EquipmentService equipmentService;

    @GetMapping("list")
    List<EquipmentResponseDto> getEquipments() {
        return equipmentService.getAll();
    }

    @GetMapping("{id}")
    EquipmentResponseDto getEquipmentById(@PathVariable("id") Long id) {
        return equipmentService.getById(id);
    }

    @PostMapping()
    EquipmentResponseDto createEquipment(@RequestBody EquipmentCreateDto equipmentBaseDto) {
        return equipmentService.create(equipmentBaseDto);
    }

    @DeleteMapping("{id}")
    void deleteEquipment(@PathVariable("id") Long id) {
        equipmentService.deleteById(id);
    }

    @PutMapping("{id}")
    EquipmentResponseDto updateEquipment(@PathVariable("id") Long id,
                                   @RequestBody EquipmentUpdateDto equipmentBaseDto) {

        return equipmentService.update(id, equipmentBaseDto);
    }
}
