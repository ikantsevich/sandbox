package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.entities.Office;
import com.exadel.sandbox.repositories.OfficeRepository;
import com.exadel.sandbox.service.OfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfficeServiceImpl implements OfficeService {

    private final OfficeRepository officeRepository;

    @Override
    public List<Office> getOffices() {
        return officeRepository.findAll();
    }

    @Override
    public Office getById(Long id) {
        return getById(id);
    }

    @Override
    public Office create(Office office) {
        return officeRepository.save(office);
    }

    @Override
    public void deleteById(Long id) {
        officeRepository.deleteById(id);
    }

    @Override
    public Office update(Long id, Office office) {
        Office byId = getById(id);
        if (byId == null)
            throw new RuntimeException("Office not found");

        office.setId(id);
        return officeRepository.save(office);
    }

    @Override
    public Office getOfficeByAddressId(Long id) {
        return null;
    }
}
