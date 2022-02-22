package com.exadel.sandbox.officeFloor.dto.officeDto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private Long addressId;
}
