package com.exadel.sandbox.employee.dto.employeeDto;

import com.exadel.sandbox.employee.dto.tgInfoDto.TgInfoResponseDto;
import com.exadel.sandbox.role.dto.RoleResponseDto;
import com.exadel.sandbox.vacation.dto.VacationResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonPropertyOrder({"id",
        "firstname",
        "lastname",
        "email",
        "position",
        "roles",
        "tg-info",
        "preferred-seat"})
public class EmployeeResponseDto extends EmployeeBaseDto {
    private Long id;

    @JsonProperty("tg-info")
    private TgInfoResponseDto tgInfoResponseDto;
    private List<RoleResponseDto> roles;
    private List<VacationResponseDto> vacations;
    @JsonProperty("preferred-seat")
    private Integer preferredSeat;
    private LocalDateTime created;
    private LocalDateTime modified;
    @JsonProperty("employment-start")
    private LocalDateTime employmentStart;

    @JsonProperty("employment-end")
    private LocalDateTime employmentEnd;
}
