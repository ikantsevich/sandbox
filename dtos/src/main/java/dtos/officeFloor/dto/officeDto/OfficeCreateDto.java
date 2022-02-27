package dtos.officeFloor.dto.officeDto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import dtos.address.dto.AddressCreateDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"addressId"})
@EqualsAndHashCode(callSuper = false)
public class OfficeCreateDto extends OfficeBaseDto {
    private AddressCreateDto address;
}
