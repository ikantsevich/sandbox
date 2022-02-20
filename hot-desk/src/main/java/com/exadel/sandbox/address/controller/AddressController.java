package com.exadel.sandbox.address.controller;

import com.exadel.sandbox.address.dto.AddressCreateDto;
import com.exadel.sandbox.address.dto.AddressResponseDto;
import com.exadel.sandbox.address.dto.AddressUpdateDto;
import com.exadel.sandbox.address.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping("list")
    List<AddressResponseDto> getAddresses() {
        return addressService.getAddresses();
    }

    @GetMapping("{id}")
    AddressResponseDto getAddressById(@PathVariable("id") Long id) {
        return addressService.getAddressById(id);
    }

    @PostMapping()
    AddressResponseDto createAddress(@RequestBody AddressCreateDto addressCreateDTO) {
        return addressService.create(addressCreateDTO);
    }

    @DeleteMapping("{id}")
    void deleteById(@PathVariable("id") Long id) {
        addressService.deleteById(id);
    }

    @PutMapping("{id}")
    AddressResponseDto updateAddress(@PathVariable("id") Long id,
                                     @RequestBody AddressUpdateDto addressUpdateDTO) {
        return addressService.update(id, addressUpdateDTO);
    }

}
