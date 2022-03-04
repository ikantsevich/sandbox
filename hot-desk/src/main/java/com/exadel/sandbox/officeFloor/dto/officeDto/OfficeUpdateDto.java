package com.exadel.sandbox.officeFloor.dto.officeDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OfficeUpdateDto extends OfficeBaseDto {
    @NotNull(message = "cannot be null")
    private Long addressId;
}
