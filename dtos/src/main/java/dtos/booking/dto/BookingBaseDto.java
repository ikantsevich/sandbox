package dtos.booking.dto;

import dtos.employee.dto.employeeDto.EmployeeResponseDto;
import lombok.Data;

@Data
public class BookingBaseDto {
    private String status;
    private EmployeeResponseDto employeeResponseDto;
}
