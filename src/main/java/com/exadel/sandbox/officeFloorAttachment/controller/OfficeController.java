package com.exadel.sandbox.officeFloorAttachment.controller;

import com.exadel.sandbox.officeFloorAttachment.dto.officeDto.OfficeCreateDto;
import com.exadel.sandbox.officeFloorAttachment.dto.officeDto.OfficeDto;
import com.exadel.sandbox.officeFloorAttachment.dto.officeDto.OfficeResponseDto;
import com.exadel.sandbox.officeFloorAttachment.dto.officeDto.OfficeUpdateDto;
import com.exadel.sandbox.officeFloorAttachment.service.OfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/office")
@RequiredArgsConstructor
public class OfficeController {

    private final OfficeService officeService;

    @GetMapping("/list")
    List<OfficeResponseDto> getOffices() {
        return officeService.getOffices();
    }

    @GetMapping("/search")
    OfficeResponseDto getOffice(@RequestParam("id") Long id) {
        return officeService.getById(id);
    }

    @GetMapping("{id}")
    OfficeResponseDto getOfficeByAddressId(@PathVariable("id") Long id) {
        return officeService.getOfficeByAddressId(id);
    }

    @PostMapping()
    OfficeResponseDto createOffice(@RequestBody OfficeCreateDto officeCreateDto) {
        return officeService.create(officeCreateDto);
    }

    @DeleteMapping("{id}")
    void deleteById(@PathVariable("id") Long id) {
        officeService.deleteById(id);
    }

    @PutMapping("{id}")
    OfficeResponseDto updateOffice(@PathVariable("id") Long id, @RequestBody OfficeUpdateDto officeUpdateDto) {
        return officeService.update(id, officeUpdateDto);
    }
}
