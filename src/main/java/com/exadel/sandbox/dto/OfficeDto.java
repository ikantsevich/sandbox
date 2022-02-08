package com.exadel.sandbox.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfficeDto {
    private Long id;
    private Long parkingId;
    private Long addressId;
    private String  officeStatus;
    private Date officeCreated;
    private Date officeModified;
}
