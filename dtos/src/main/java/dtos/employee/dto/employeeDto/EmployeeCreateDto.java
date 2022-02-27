package dtos.employee.dto.employeeDto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import dtos.employee.dto.tgInfoDto.TgInfoCreateDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonPropertyOrder({"firstname", "lastname"})
public class EmployeeCreateDto extends EmployeeBaseDto {
    private List<String> roles;
    private TgInfoCreateDto tgInfoCreateDto;
    private LocalDateTime employmentStart;
    private LocalDateTime employmentEnd;
}
