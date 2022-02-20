package com.exadel.sandbox.officeFloor.service;

import com.exadel.sandbox.officeFloor.dto.officeDto.OfficeCreateDto;
import com.exadel.sandbox.officeFloor.dto.officeDto.OfficeResponseDto;
import com.exadel.sandbox.officeFloor.dto.officeDto.OfficeUpdateDto;

import java.util.List;

public interface OfficeService {
    List<OfficeResponseDto> getOffices();

    OfficeResponseDto getById(Long id);

    OfficeResponseDto create(OfficeCreateDto officeCreateDto);

    void deleteById(Long id);

    OfficeResponseDto update(Long id, OfficeUpdateDto officeUpdateDto);
}
