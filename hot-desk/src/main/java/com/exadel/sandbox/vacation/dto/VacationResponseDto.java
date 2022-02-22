package com.exadel.sandbox.vacation.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonPropertyOrder({"id", "employeeId"})
public class VacationResponseDto extends VacationBaseDto {
    private Long id;
    private Long employeeId;
    private LocalDateTime created;
    private LocalDateTime modified;
}
