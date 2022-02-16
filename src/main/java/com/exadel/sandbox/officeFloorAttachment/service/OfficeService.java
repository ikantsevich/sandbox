package com.exadel.sandbox.officeFloorAttachment.service;

import com.exadel.sandbox.officeFloorAttachment.dto.officeDto.OfficeCreateDto;
import com.exadel.sandbox.officeFloorAttachment.dto.officeDto.OfficeDto;
import com.exadel.sandbox.officeFloorAttachment.dto.officeDto.OfficeResponseDto;
import com.exadel.sandbox.officeFloorAttachment.dto.officeDto.OfficeUpdateDto;

import java.util.List;

public interface OfficeService {
    List<OfficeResponseDto> getOffices();

    OfficeResponseDto getById(Long id);

    OfficeResponseDto create(OfficeCreateDto officeCreateDto);

    void deleteById(Long id);

    OfficeResponseDto update(Long id, OfficeUpdateDto officeUpdateDto);

    OfficeResponseDto getOfficeByAddressId(Long id);
}
