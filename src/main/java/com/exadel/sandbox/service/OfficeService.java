package com.exadel.sandbox.service;

import com.exadel.sandbox.dto.OfficeDto;
import com.exadel.sandbox.entities.Office;

import java.util.List;

public interface OfficeService {
    List<Office> getOffices();

    Office getById(Long id);

    Office create(Office office);

    void deleteById(Long id);

    Office update(Long id, Office office);

    Office getOfficeByAddressId(Long id);
}
