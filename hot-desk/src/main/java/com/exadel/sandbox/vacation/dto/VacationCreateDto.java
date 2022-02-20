package com.exadel.sandbox.vacation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class VacationCreateDto extends VacationBaseDto {
    @JsonProperty("employee-id")
    private Long employeeId;
}
