package com.exadel.sandbox.equipment.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class EquipmentUpdateDto extends EquipmentBaseDto {
    private Long seatId;
}
