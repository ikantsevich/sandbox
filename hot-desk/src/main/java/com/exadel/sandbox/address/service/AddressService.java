package com.exadel.sandbox.address.service;

import com.exadel.sandbox.address.entity.Address;
import com.exadel.sandbox.address.dto.AddressCreateDto;
import com.exadel.sandbox.address.dto.AddressResponseDto;
import com.exadel.sandbox.address.dto.AddressUpdateDto;
import com.exadel.sandbox.address.repository.AddressRepository;
import com.exadel.sandbox.base.BaseCrudService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class AddressService extends BaseCrudService<Address, AddressResponseDto, AddressUpdateDto, AddressCreateDto, AddressRepository> {

    public AddressService(ModelMapper mapper, AddressRepository repository) {
        super(mapper, repository);
    }
}
