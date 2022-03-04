package com.exadel.sandbox.equipment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentBaseDto {
    @NotEmpty(message = "cannot be empty")
    private String name;
}
