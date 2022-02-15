package com.exadel.sandbox.officeFloorAttachment.service.impl;

import com.exadel.sandbox.officeFloorAttachment.dto.officeDto.OfficeCreateDto;
import com.exadel.sandbox.officeFloorAttachment.dto.officeDto.OfficeResponseDto;
import com.exadel.sandbox.officeFloorAttachment.dto.officeDto.OfficeUpdateDto;
import com.exadel.sandbox.officeFloorAttachment.entities.Office;
import com.exadel.sandbox.exception.EntityNotFoundException;
import com.exadel.sandbox.officeFloorAttachment.repositories.OfficeRepository;
import com.exadel.sandbox.officeFloorAttachment.service.OfficeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OfficeServiceImpl implements OfficeService {

    private final OfficeRepository officeRepository;

    private final ModelMapper mapper;


    @Override
    public List<OfficeResponseDto> getOffices() {
        List<Office> officeList = officeRepository.findAll();
        return officeList.stream().map(office -> mapper.map(office, OfficeResponseDto.class)).collect(Collectors.toList());
    }

    @Override
    public OfficeResponseDto getById(Long id) {
        Optional<Office> office1 = officeRepository.findById(id);
        Office office = office1.orElseThrow(() -> new EntityNotFoundException("Office with id " + id + " not found"));
        return mapper.map(office, OfficeResponseDto.class);
    }

    @Override
    public OfficeResponseDto create(OfficeCreateDto officeCreateDto) {
        Office office = mapper.map(officeCreateDto, Office.class);
        Office savedOffice = officeRepository.save(office);
        return mapper.map(savedOffice, OfficeResponseDto.class);
    }

    @Override
    public void deleteById(Long id) {
        officeRepository.deleteById(id);
    }

    @Override
    public OfficeResponseDto update(Long id, OfficeUpdateDto officeUpdateDto) {
        Office office = mapper.map(officeUpdateDto, Office.class);
        OfficeResponseDto officeResponseDtoById = getById(id);
        if (officeResponseDtoById == null)
            throw new EntityNotFoundException("Office not found with this " + id);

        office.setId(id);
        Office savedOffice = officeRepository.save(office);
        return mapper.map(savedOffice, OfficeResponseDto.class);
    }

    @Override
    public OfficeResponseDto getOfficeByAddressId(Long id) {
        Office officeByAddressId = officeRepository.getOfficeByAddressId(id);
        return mapper.map(officeByAddressId, OfficeResponseDto.class);
    }
}
