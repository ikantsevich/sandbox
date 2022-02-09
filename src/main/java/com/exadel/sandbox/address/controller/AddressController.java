package com.exadel.sandbox.address.controller;

import com.exadel.sandbox.address.dto.AddressBaseDto;
import com.exadel.sandbox.address.entity.Address;
import com.exadel.sandbox.address.dto.AddressResponseDto;
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
    List<AddressResponseDto> getAddresses(){
        return addressService.getAddresses();
    }

    @GetMapping("{id}")
    AddressResponseDto getAddressById(@PathVariable("id") Long id){
        return addressService.getAddressById(id);
    }

    @PostMapping()
    AddressBaseDto createAddress(@RequestBody AddressBaseDto addressBaseDto){
        return addressService.create(addressBaseDto);
    }

    @DeleteMapping("{id}")
    void deleteById(@PathVariable("id") Long id){
        addressService.deleteById(id);
    }

    @PutMapping("{id}")
    AddressBaseDto updateAddress(@PathVariable("id") Long id,
                                 @RequestBody AddressBaseDto addressBaseDto){
        return addressService.update(id, addressBaseDto);
    }

}
