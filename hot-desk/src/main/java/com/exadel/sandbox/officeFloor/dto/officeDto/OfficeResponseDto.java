package com.exadel.sandbox.officeFloor.dto.officeDto;

import com.exadel.sandbox.attachment.repository.address.dto.AddressResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfficeResponseDto {
    private Long id;
    private AddressResponseDto address;
    private String officeStatus;
    private LocalDateTime created;
    private LocalDateTime modified;
}
