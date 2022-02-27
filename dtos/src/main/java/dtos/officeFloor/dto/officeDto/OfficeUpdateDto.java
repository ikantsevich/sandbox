package dtos.officeFloor.dto.officeDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OfficeUpdateDto extends OfficeBaseDto {
    private Long addressId;
}
