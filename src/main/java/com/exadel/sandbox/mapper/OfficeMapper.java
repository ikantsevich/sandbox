package com.exadel.sandbox.mapper;

import com.exadel.sandbox.dto.OfficeDto;
import com.exadel.sandbox.entities.Office;

public interface OfficeMapper {
    Office toEntity(OfficeDto officeDto);
    OfficeDto toDto(Office office);
}
