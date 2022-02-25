package com.exadel.sandbox.officeFloor.service;

import com.exadel.sandbox.address.dto.AddressResponseDto;
import com.exadel.sandbox.address.repository.AddressRepository;
import com.exadel.sandbox.base.BaseCrudService;
import com.exadel.sandbox.exception.EntityNotFoundException;
import com.exadel.sandbox.officeFloor.dto.officeDto.OfficeCreateDto;
import com.exadel.sandbox.officeFloor.dto.officeDto.OfficeResponseDto;
import com.exadel.sandbox.officeFloor.dto.officeDto.OfficeUpdateDto;
import com.exadel.sandbox.officeFloor.entities.Office;
import com.exadel.sandbox.officeFloor.repositories.OfficeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OfficeService extends BaseCrudService<Office, OfficeResponseDto, OfficeUpdateDto, OfficeCreateDto, OfficeRepository> {
    public OfficeService(ModelMapper mapper, OfficeRepository repository) {
        super(mapper, repository);
    }
}
