package com.exadel.sandbox.address.controller;

import com.exadel.sandbox.address.service.AddressService;
import dtos.address.dto.AddressCreateDto;
import dtos.address.dto.AddressResponseDto;
import dtos.address.dto.AddressUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping("list")
    ResponseEntity<List<AddressResponseDto>> getAddresses() {
        return addressService.getList();
    }

    @GetMapping("{id}")
    ResponseEntity<AddressResponseDto> getAddressById(@PathVariable("id") Long id) {
        return addressService.getById(id);
    }

    @PostMapping()
    ResponseEntity<AddressResponseDto> createAddress(@RequestBody AddressCreateDto addressCreateDTO) {
        return addressService.create(addressCreateDTO);
    }

    @DeleteMapping("{id}")
    void deleteById(@PathVariable("id") Long id) {
        addressService.delete(id);
    }

    @PutMapping("{id}")
    ResponseEntity<AddressResponseDto> updateAddress(@PathVariable("id") Long id,
                                                     @RequestBody AddressUpdateDto addressUpdateDTO) {
        return addressService.update(id, addressUpdateDTO);
    }

}
