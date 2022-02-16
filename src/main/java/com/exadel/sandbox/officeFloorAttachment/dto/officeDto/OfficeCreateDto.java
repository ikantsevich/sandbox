package com.exadel.sandbox.officeFloorAttachment.dto.officeDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfficeCreateDto {
    private Long parkingId;
    private Long addressId;
}
