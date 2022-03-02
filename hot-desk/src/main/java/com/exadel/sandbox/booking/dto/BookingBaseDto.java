package com.exadel.sandbox.booking.dto;

import com.exadel.sandbox.employee.dto.employeeDto.EmployeeResponseDto;
import lombok.Data;

@Data
public class BookingBaseDto {
    private Long employeeId;
    private Long seatId;
}
