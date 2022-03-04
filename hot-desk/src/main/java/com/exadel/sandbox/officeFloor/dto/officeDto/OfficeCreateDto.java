package com.exadel.sandbox.officeFloor.dto.officeDto;

import com.exadel.sandbox.address.dto.AddressCreateDto;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
