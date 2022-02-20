package com.exadel.sandbox.officeFloor.controller;

import com.exadel.sandbox.officeFloor.dto.officeDto.OfficeCreateDto;
import com.exadel.sandbox.officeFloor.dto.officeDto.OfficeResponseDto;
import com.exadel.sandbox.officeFloor.dto.officeDto.OfficeUpdateDto;
import com.exadel.sandbox.officeFloor.service.OfficeService;
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

    @GetMapping("{id}")
    OfficeResponseDto getOffice(@PathVariable Long id) {
        return officeService.getById(id);
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
