package com.exadel.sandbox.officeFloor.service;

import com.exadel.sandbox.base.BaseCrudService;
import com.exadel.sandbox.officeFloor.entities.Office;
import com.exadel.sandbox.officeFloor.repositories.OfficeRepository;
import dtos.officeFloor.dto.officeDto.OfficeCreateDto;
import dtos.officeFloor.dto.officeDto.OfficeResponseDto;
import dtos.officeFloor.dto.officeDto.OfficeUpdateDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OfficeService extends BaseCrudService<Office, OfficeResponseDto, OfficeUpdateDto, OfficeCreateDto, OfficeRepository> {
    public OfficeService(ModelMapper mapper, OfficeRepository repository) {
        super(mapper, repository);
    }
}
