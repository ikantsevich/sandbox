package com.exadel.sandbox.officeFloor.dto.officeDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfficeBaseDto {
    @NotNull(message = "cannot be null")
    private String officeStatus;
}
