package com.exadel.sandbox.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private Long id;
    private TgInfoDto tgInfoDto;
    private String firstname;
    private String lastname;
    private String email;
    private String position;
    private Integer preferredSeat;
    private LocalDateTime employmentStart;
    private LocalDateTime employmentEnd;
    private LocalDateTime created;
    private LocalDateTime modified;
}
