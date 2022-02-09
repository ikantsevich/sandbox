package com.exadel.sandbox.address.service;

import com.exadel.sandbox.address.dto.AddressBaseDto;
import com.exadel.sandbox.address.dto.AddressResponseDto;

import java.util.List;

public interface AddressService {

    List<AddressResponseDto> getAddresses();

    AddressResponseDto getAddressById(Long id);

    AddressBaseDto create(AddressBaseDto customer);

    void deleteById(Long id);

    AddressBaseDto update(Long id, AddressBaseDto customer);

}
