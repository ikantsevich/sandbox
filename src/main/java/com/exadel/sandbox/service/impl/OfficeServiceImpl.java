package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.OfficeDto;
import com.exadel.sandbox.entities.Office;
import com.exadel.sandbox.exception.EntityNotFoundException;
import com.exadel.sandbox.mapper.OfficeMapper;
import com.exadel.sandbox.repositories.OfficeRepository;
import com.exadel.sandbox.service.OfficeService;
import lombok.RequiredArgsConstructor;
import org.hibernate.cfg.JoinedSubclassFkSecondPass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class OfficeServiceImpl implements OfficeService {

    @Autowired
    OfficeRepository officeRepository;
    @Autowired
    OfficeMapper officeMapper;


    @Override
    public List<OfficeDto> getOffices() {
        List<Office> all = officeRepository.findAll();
        List<OfficeDto> list = new ArrayList<>();
        for (Office office: all){
            list.add(officeMapper.toDto(office));
        }
        return list;
    }

    @Override
    public OfficeDto getById(Long id) {
        Optional<Office> one = officeRepository.findById(id);
        Office office = one.orElseThrow(() -> new EntityNotFoundException("Office with id " + id + " not found"));
        return officeMapper.toDto(office);
    }

    @Override
    public OfficeDto create(OfficeDto officeDto) {
        Office office = officeMapper.toEntity(officeDto);
        Office save = officeRepository.save(office);
        return officeMapper.toDto(save);
    }

    @Override
    public void deleteById(Long id) {
        officeRepository.deleteById(id);
    }

    @Override
    public OfficeDto update(Long id, OfficeDto officeDto) {
        Office office = officeMapper.toEntity(officeDto);
        OfficeDto byId = getById(id);
        if (byId == null)
            throw new RuntimeException("Office not found");

        office.setId(id);
        Office save = officeRepository.save(office);
        return officeMapper.toDto(save);
    }

    @Override
    public OfficeDto getOfficeByAddressId(Long id) {
        Office officeByAddressId = officeRepository.getOfficeByAddressId(id);
        return officeMapper.toDto(officeByAddressId);
    }
}
