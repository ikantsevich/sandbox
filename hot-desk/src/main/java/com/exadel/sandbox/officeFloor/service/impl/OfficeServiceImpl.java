package com.exadel.sandbox.officeFloor.service.impl;

import com.exadel.sandbox.address.dto.AddressResponseDto;
import com.exadel.sandbox.address.repository.AddressRepository;
import com.exadel.sandbox.exception.EntityNotFoundException;
import com.exadel.sandbox.officeFloor.dto.officeDto.OfficeCreateDto;
import com.exadel.sandbox.officeFloor.dto.officeDto.OfficeResponseDto;
import com.exadel.sandbox.officeFloor.dto.officeDto.OfficeUpdateDto;
import com.exadel.sandbox.officeFloor.entities.Office;
import com.exadel.sandbox.officeFloor.repositories.OfficeRepository;
import com.exadel.sandbox.officeFloor.service.OfficeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OfficeServiceImpl implements OfficeService {

    private final OfficeRepository officeRepository;
    private final AddressRepository addressRepository;
    private final ModelMapper mapper;


    @Override
    public List<OfficeResponseDto> getOffices() {
        List<Office> officeList = officeRepository.findAll();

        return officeList.stream().map(this::officeToResponseDto).collect(Collectors.toList());
    }

    @Override
    public OfficeResponseDto getById(Long id) {
        Office office = officeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Office with id " + id + " not found"));
        return officeToResponseDto(office);
    }

    @Override
    public OfficeResponseDto create(OfficeCreateDto officeCreateDto) {
        Office office = mapper.map(officeCreateDto, Office.class);
        office.setAddress(addressRepository.findById(officeCreateDto.getAddressId()).orElseThrow(
                () -> new EntityNotFoundException("Address with id: " + officeCreateDto.getAddressId() + " not found")));

        Office savedOffice = officeRepository.save(office);
        return officeToResponseDto(office);
    }

    @Override
    public void deleteById(Long id) {
        officeRepository.deleteById(id);
    }

    @Override
    public OfficeResponseDto update(Long id, OfficeUpdateDto officeUpdateDto) {

        Office office = officeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Office with id:  " + id + " not found"));
        office.setAddress(addressRepository.findById(officeUpdateDto.getAddressId()).orElseThrow(
                () -> new EntityNotFoundException("Address with id: " + officeUpdateDto.getAddressId() + " not found")
        ));

        return officeToResponseDto(office);
    }

    private OfficeResponseDto officeToResponseDto(Office office) {
        OfficeResponseDto map = mapper.map(office, OfficeResponseDto.class);
        if (office.getAddress() != null)
            map.setAddress(mapper.map(office.getAddress(), AddressResponseDto.class));
        return map;
    }
}
