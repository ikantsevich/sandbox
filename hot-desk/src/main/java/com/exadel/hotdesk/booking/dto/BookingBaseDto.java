package com.exadel.hotdesk.booking.dto;

import com.exadel.hotdesk.employee.dto.employeeDto.EmployeeResponseDto;
import lombok.Data;

@Data
public class BookingBaseDto {

    private String status;
    private EmployeeResponseDto employeeResponseDto;

}
