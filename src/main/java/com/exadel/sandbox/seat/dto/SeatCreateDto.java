package com.exadel.sandbox.seat.dto;

import com.exadel.sandbox.equipment.dto.EquipmentBaseDto;
import com.exadel.sandbox.equipment.entity.Equipment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatCreateDto extends SeatBaseDto{
    private Set<EquipmentBaseDto> equipmentBaseDtos;
}
