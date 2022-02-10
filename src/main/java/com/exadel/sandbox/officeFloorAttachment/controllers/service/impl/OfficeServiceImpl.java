package com.exadel.sandbox.officeFloorAttachment.controllers.service.impl;

import com.exadel.sandbox.officeFloorAttachment.controllers.dto.OfficeDto;
import com.exadel.sandbox.officeFloorAttachment.controllers.entities.Office;
import com.exadel.sandbox.officeFloorAttachment.controllers.exception.EntityNotFoundException;
import com.exadel.sandbox.officeFloorAttachment.controllers.repositories.OfficeRepository;
import com.exadel.sandbox.officeFloorAttachment.controllers.service.OfficeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OfficeServiceImpl implements OfficeService {


    private final OfficeRepository officeRepository;

    private final ModelMapper mapper;


    @Override
    public List<OfficeDto> getOffices() {
        List<Office> all = officeRepository.findAll();
        List<OfficeDto> list = new ArrayList<>();
        for (Office office: all){
            list.add(mapper.map(office, OfficeDto.class));
        }
        return list;
    }

    @Override
    public OfficeDto getById(Long id) {
        Optional<Office> one = officeRepository.findById(id);
        Office office = one.orElseThrow(() -> new EntityNotFoundException("Office with id " + id + " not found"));
        return mapper.map(office, OfficeDto.class);
    }

    @Override
    public OfficeDto create(OfficeDto officeDto) {
        Office office = mapper.map(officeDto, Office.class);
        Office save = officeRepository.save(office);
        return mapper.map(save, OfficeDto.class);
    }

    @Override
    public void deleteById(Long id) {
        officeRepository.deleteById(id);
    }

    @Override
    public OfficeDto update(Long id, OfficeDto officeDto) {
        Office office = mapper.map(officeDto, Office.class);
        OfficeDto byId = getById(id);
        if (byId == null)
            throw new RuntimeException("Office not found");

        office.setId(id);
        Office save = officeRepository.save(office);
        return mapper.map(save, OfficeDto.class);
    }

    @Override
    public OfficeDto getOfficeByAddressId(Long id) {
        Office officeByAddressId = officeRepository.getOfficeByAddressId(id);
        return mapper.map(officeByAddressId, OfficeDto.class);
    }
}
