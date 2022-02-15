package com.exadel.hotdesk.address.service;

import com.exadel.hotdesk.address.dto.AddressCreateDto;
import com.exadel.hotdesk.address.dto.AddressResponseDto;
import com.exadel.hotdesk.address.entity.Address;
import com.exadel.hotdesk.exception.EntityNotFoundException;
import com.exadel.hotdesk.address.dto.AddressUpdateDto;
import com.exadel.hotdesk.address.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final ModelMapper mapper;

    @Override
    public List<AddressResponseDto> getAddresses() {
        List<Address> addresses = addressRepository.findAll();
        return addresses.stream()
                .map(address -> mapper.map(address, AddressResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public AddressResponseDto getAddressById(Long id) {
        Optional<Address> addressByID = addressRepository.findById(id);
        Address address = addressByID.orElseThrow(
                () -> new EntityNotFoundException("Can\'t get Address with ID: " + id + ". Doesn\'t exist."));
        return mapper.map(address, AddressResponseDto.class);
    }

    @Override
    public AddressResponseDto create(AddressCreateDto addressCreateDto) {
        Address employee = mapper.map(addressCreateDto, Address.class);
        return mapper.map(addressRepository.save(employee), AddressResponseDto.class);
    }

    @Override
    public void deleteById(Long id) {
        addressRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can\'t delete Address with ID: " + id + ". Doesn\'t exist."));
        addressRepository.deleteById(id);
    }

    @Override
    public AddressResponseDto update(Long id, AddressUpdateDto addressUpdateDto) {
        Address address = addressRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can\'t update Address with ID: " + id + ". Doesn\'t exist."));
        mapper.map(addressUpdateDto, address);
        return mapper.map(addressRepository.save(address), AddressResponseDto.class);
    }

}
