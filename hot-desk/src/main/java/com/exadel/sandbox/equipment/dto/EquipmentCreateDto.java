package com.exadel.sandbox.equipment.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EquipmentCreateDto extends EquipmentBaseDto {
    private Long seatId;
}
