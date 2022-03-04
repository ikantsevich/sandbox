package com.exadel.sandbox.vacation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;


@Data
@EqualsAndHashCode(callSuper = true)
public class VacationUpdateDto extends VacationBaseDto {
    @NotNull(message = "cannot be null")
    private Long employeeId;
}
