package com.exadel.sandbox.officeFloor.controller;

import com.exadel.sandbox.officeFloor.dto.officeDto.OfficeCreateDto;
import com.exadel.sandbox.officeFloor.dto.officeDto.OfficeResponseDto;
import com.exadel.sandbox.officeFloor.dto.officeDto.OfficeUpdateDto;
import com.exadel.sandbox.officeFloor.service.OfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/office")
@RequiredArgsConstructor
public class OfficeController {

    private final OfficeService officeService;

    @GetMapping("/list")
    ResponseEntity<List<OfficeResponseDto>> getOffices() {
        return officeService.getList();
    }

    @GetMapping("{id}")
    ResponseEntity<OfficeResponseDto> getOffice(@PathVariable Long id) {
        return officeService.getById(id);
    }

    @PostMapping()
    ResponseEntity<OfficeResponseDto> createOffice(@RequestBody OfficeCreateDto officeCreateDto) {
        return officeService.create(officeCreateDto);
    }

    @DeleteMapping("{id}")
    void deleteById(@PathVariable("id") Long id) {
        officeService.delete(id);
    }

    @PutMapping("{id}")
    ResponseEntity<OfficeResponseDto> updateOffice(@PathVariable("id") Long id, @RequestBody OfficeUpdateDto officeUpdateDto) {
        return officeService.update(id, officeUpdateDto);
    }
}
