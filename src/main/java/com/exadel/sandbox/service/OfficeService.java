package com.exadel.sandbox.service;

import com.exadel.sandbox.dto.OfficeDto;
import com.exadel.sandbox.entities.Office;

import java.util.List;

public interface OfficeService {
    List<OfficeDto> getOffices();

    OfficeDto getById(Long id);

    OfficeDto create(OfficeDto officeDto);

    void deleteById(Long id);

    OfficeDto update(Long id, OfficeDto officeDto);

    OfficeDto getOfficeByAddressId(Long id);
}
