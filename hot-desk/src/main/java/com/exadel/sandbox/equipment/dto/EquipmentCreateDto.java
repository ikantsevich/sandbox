package com.exadel.sandbox.equipment.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EquipmentCreateDto extends EquipmentBaseDto {
    @NotNull(message = "cannot be null")
    private Long seatId;
}
