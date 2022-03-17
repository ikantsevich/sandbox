package com.exadel.sandbox.equipment.controller;

import com.exadel.sandbox.equipment.dto.EquipmentCreateDto;
import com.exadel.sandbox.equipment.dto.EquipmentResponseDto;
import com.exadel.sandbox.equipment.dto.EquipmentUpdateDto;
import com.exadel.sandbox.equipment.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("equipment")
@RequiredArgsConstructor
public class EquipmentController {
    private final EquipmentService equipmentService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MAPPER')")
    @GetMapping("list")
    ResponseEntity<List<EquipmentResponseDto>> getEquipments() {
        return equipmentService.getList();
    }

    @GetMapping("{id}")
    ResponseEntity<EquipmentResponseDto> getEquipmentById(@PathVariable("id") Long id) {
        return equipmentService.getById(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MAPPER')")
    @PostMapping()
    ResponseEntity<EquipmentResponseDto> createEquipment(@Valid @RequestBody EquipmentCreateDto equipmentCreateDto) {
        return equipmentService.create(equipmentCreateDto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MAPPER')")
    @DeleteMapping("{id}")
    void deleteEquipment(@PathVariable("id") Long id) {
        equipmentService.delete(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MAPPER')")
    @PutMapping("{id}")
    ResponseEntity<EquipmentResponseDto> updateEquipment(@PathVariable("id") Long id,
                                                         @Valid @RequestBody EquipmentUpdateDto equipmentUpdateDto) {

        return equipmentService.update(id, equipmentUpdateDto);
    }
}
