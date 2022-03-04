package com.exadel.sandbox.officeFloor.dto.floorDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FloorCreateDto extends FloorBaseDto {
    @NotNull(message = "cannot be null")
    private Long officeId;
}
