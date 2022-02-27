package com.exadel.sandbox.equipment.controller;

import com.exadel.sandbox.equipment.service.EquipmentService;
import dtos.equipment.dto.EquipmentCreateDto;
import dtos.equipment.dto.EquipmentResponseDto;
import dtos.equipment.dto.EquipmentUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("equipment")
@RequiredArgsConstructor
public class EquipmentController {
    private final EquipmentService equipmentService;

    @GetMapping("list")
    ResponseEntity<List<EquipmentResponseDto>> getEquipments() {
        return equipmentService.getList();
    }

    @GetMapping("{id}")
    ResponseEntity<EquipmentResponseDto> getEquipmentById(@PathVariable("id") Long id) {
        return equipmentService.getById(id);
    }

    @PostMapping()
    ResponseEntity<EquipmentResponseDto> createEquipment(@RequestBody EquipmentCreateDto equipmentCreateDto) {
        return equipmentService.create(equipmentCreateDto);
    }

    @DeleteMapping("{id}")
    void deleteEquipment(@PathVariable("id") Long id) {
        equipmentService.delete(id);
    }

    @PutMapping("{id}")
    ResponseEntity<EquipmentResponseDto> updateEquipment(@PathVariable("id") Long id,
                                                         @RequestBody EquipmentUpdateDto equipmentUpdateDto) {

        return equipmentService.update(id, equipmentUpdateDto);
    }
}
