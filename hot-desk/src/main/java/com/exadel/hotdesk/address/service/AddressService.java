package com.exadel.hotdesk.address.service;

import com.exadel.hotdesk.address.dto.AddressCreateDto;
import com.exadel.hotdesk.address.dto.AddressResponseDto;
import com.exadel.hotdesk.address.dto.AddressUpdateDto;

import java.util.List;

public interface AddressService {

    List<AddressResponseDto> getAddresses();

    AddressResponseDto getAddressById(Long id);

    AddressResponseDto create(AddressCreateDto addressCreateDto);

    void deleteById(Long id);

    AddressResponseDto update(Long id, AddressUpdateDto addressUpdateDto);

}
