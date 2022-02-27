package dtos.employee.dto.employeeDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class EmployeeUpdateDto extends EmployeeBaseDto {
    private LocalDateTime employmentStart;
    private LocalDateTime employmentEnd;
}
