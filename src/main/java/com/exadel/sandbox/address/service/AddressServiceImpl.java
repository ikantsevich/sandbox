package com.exadel.sandbox.address.service;

import com.exadel.sandbox.address.dto.AddressBaseDto;
import com.exadel.sandbox.address.dto.AddressResponseDto;
import com.exadel.sandbox.address.entity.Address;
import com.exadel.sandbox.address.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final ModelMapper mapper;

    @Override
    public List<AddressResponseDto> getAddresses() {
        List<Address> addresses = addressRepository.findAll();
        List<AddressResponseDto> addressResponseDtos = new ArrayList<>();
        for (Address address : addresses) {
            addressResponseDtos.add(mapper.map(address, AddressResponseDto.class));
        }
        return addressResponseDtos;
    }

    @Override
    public AddressResponseDto getAddressById(Long id) {
        Optional<Address> addressByID = addressRepository.findById(id);
        Address address = addressByID.orElseThrow(
                () -> new IllegalArgumentException("Can\'t get Address with ID: " + id + ". Doesn\'t exist."));
        return mapper.map(address, AddressResponseDto.class);
    }

    @Override
    public AddressBaseDto create(AddressBaseDto addressBaseDto) {
        Address employee = mapper.map(addressBaseDto, Address.class);
        return mapper.map(addressRepository.save(employee), AddressResponseDto.class);
    }

    @Override
    public void deleteById(Long id) {
        Address address = addressRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Can\'t delete Address with ID: " + id + ". Doesn\'t exist."));
        addressRepository.deleteById(id);
    }

    @Override
    public AddressBaseDto update(Long id, AddressBaseDto addressBaseDto) {
        Address address = addressRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Can\'t update Address with ID: " + id + ". Doesn\'t exist."));
        mapper.map(addressBaseDto, address);
        return mapper.map(addressRepository.save(address), AddressResponseDto.class);
    }

}
