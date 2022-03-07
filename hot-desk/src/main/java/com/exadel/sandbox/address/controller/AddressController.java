package com.exadel.sandbox.address.controller;

import com.exadel.sandbox.address.dto.AddressCreateDto;
import com.exadel.sandbox.address.dto.AddressResponseDto;
import com.exadel.sandbox.address.dto.AddressUpdateDto;
import com.exadel.sandbox.address.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_MAPPER')")
    @GetMapping("list")
    ResponseEntity<List<AddressResponseDto>> getAddresses() {
        return addressService.getList();
    }

    @GetMapping("{id}")
    ResponseEntity<AddressResponseDto> getAddressById(@PathVariable("id") Long id) {
        return addressService.getById(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MAPPER')")
    @PostMapping()
    ResponseEntity<AddressResponseDto> createAddress(@Valid @RequestBody AddressCreateDto addressCreateDTO) {
        return addressService.create(addressCreateDTO);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MAPPER')")
    @DeleteMapping("{id}")
    void deleteById(@PathVariable("id") Long id) {
        addressService.delete(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MAPPER')")
    @PutMapping("{id}")
    ResponseEntity<AddressResponseDto> updateAddress(@PathVariable("id") Long id,
                                                     @Valid @RequestBody AddressUpdateDto addressUpdateDTO) {
        return addressService.update(id, addressUpdateDTO);
    }

}
