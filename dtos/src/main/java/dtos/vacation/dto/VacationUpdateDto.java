package dtos.vacation.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class VacationUpdateDto extends VacationBaseDto {
    private Long employeeId;
}
