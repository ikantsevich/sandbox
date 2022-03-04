package com.exadel.sandbox.equipment.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class EquipmentUpdateDto extends EquipmentBaseDto {
    @NotNull(message = "cannot be null")
    private Long seatId;
}
