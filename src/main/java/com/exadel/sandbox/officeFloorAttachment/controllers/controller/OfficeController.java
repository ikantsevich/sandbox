package com.exadel.sandbox.officeFloorAttachment.controllers.controller;

import com.exadel.sandbox.officeFloorAttachment.controllers.dto.OfficeDto;
import com.exadel.sandbox.officeFloorAttachment.controllers.service.OfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/office")
@RequiredArgsConstructor
public class OfficeController {

    private final OfficeService officeService;

    @GetMapping("/list")
    List<OfficeDto> getOffices() {
        return officeService.getOffices();
    }

    @GetMapping("/search")
    OfficeDto getOffice(@RequestParam("id") Long id) {
        return officeService.getById(id);
    }

    @GetMapping("{id}")
    OfficeDto getOfficeByAddressId(@PathVariable("id") Long id) {
        return officeService.getOfficeByAddressId(id);
    }

    @PostMapping()
    OfficeDto createOffice(@RequestBody OfficeDto officeDto) {
        return officeService.create(officeDto);
    }

    @DeleteMapping("{id}")
    void deleteById(@PathVariable("id") Long id) {
        officeService.deleteById(id);
    }

    @PutMapping("{id}")
    OfficeDto updateOffice(@PathVariable("id") Long id, @RequestBody OfficeDto officeDto) {
        return officeService.update(id, officeDto);
    }
}
