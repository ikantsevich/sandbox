package com.exadel.sandbox.seat.dto;

import com.exadel.sandbox.equipment.dto.EquipmentResponseDto;
import com.exadel.sandbox.equipment.entity.Equipment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SeatResponseDto extends SeatBaseDto{
    private Long id;
    private Set<EquipmentResponseDto> equipmentResponseDtos;
    private Long floorId;
    private LocalDateTime created;
    private LocalDateTime modified;
}
