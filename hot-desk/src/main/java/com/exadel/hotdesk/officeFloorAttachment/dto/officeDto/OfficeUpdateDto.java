package com.exadel.sandbox.officeFloorAttachment.dto.officeDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfficeUpdateDto {
    private Long parkingId;
    private Long addressId;
    private String officeStatus;
    private LocalDateTime officeModified;
}
