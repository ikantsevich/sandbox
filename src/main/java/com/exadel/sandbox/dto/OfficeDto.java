package com.exadel.sandbox.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OfficeDto {
    private Long id;
    private Long parkingId;
    private Long addressId;
    private String  officeStatus;
    private Date officeCreated;
    private Date officeModified;
}
