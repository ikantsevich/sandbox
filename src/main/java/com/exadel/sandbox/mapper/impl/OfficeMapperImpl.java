package com.exadel.sandbox.mapper.impl;

import com.exadel.sandbox.dto.OfficeDto;
import com.exadel.sandbox.entities.Office;
import com.exadel.sandbox.mapper.OfficeMapper;

public class OfficeMapperImpl implements OfficeMapper {
    @Override
    public Office toEntity(OfficeDto officeDto) {
        return new Office(
                officeDto.getId(),
                officeDto.getParkingId(),
                officeDto.getAddressId(),
                officeDto.getOfficeStatus(),
                officeDto.getOfficeCreated(),
                officeDto.getOfficeModified()
        );

    }

    @Override
    public OfficeDto toDto(Office office) {
        return new OfficeDto(
                office.getId(),
                office.getParkingId(),
                office.getAddressId(),
                office.getOfficeStatus(),
                office.getOfficeCreated(),
                office.getOfficeModified()
        );
    }
}
