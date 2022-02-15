package com.exadel.sandbox.address.service;

import com.exadel.sandbox.address.dto.AddressCreateDto;
import com.exadel.sandbox.address.dto.AddressResponseDto;
import com.exadel.sandbox.address.dto.AddressUpdateDto;

import java.util.List;

public interface AddressService {

    List<AddressResponseDto> getAddresses();

    AddressResponseDto getAddressById(Long id);

    AddressResponseDto create(AddressCreateDto addressCreateDto);

    void deleteById(Long id);

    AddressResponseDto update(Long id, AddressUpdateDto addressUpdateDto);

}
