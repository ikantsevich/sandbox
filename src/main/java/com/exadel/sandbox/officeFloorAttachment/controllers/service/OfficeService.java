package com.exadel.sandbox.officeFloorAttachment.controllers.service;

import com.exadel.sandbox.officeFloorAttachment.controllers.dto.OfficeDto;
import org.springframework.stereotype.Component;

import java.util.List;

public interface OfficeService {
    List<OfficeDto> getOffices();

    OfficeDto getById(Long id);

    OfficeDto create(OfficeDto officeDto);

    void deleteById(Long id);

    OfficeDto update(Long id, OfficeDto officeDto);

    OfficeDto getOfficeByAddressId(Long id);
}
