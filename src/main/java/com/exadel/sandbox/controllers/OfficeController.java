package com.exadel.sandbox.controllers;

import com.exadel.sandbox.dto.OfficeDto;
import com.exadel.sandbox.entities.Office;
import com.exadel.sandbox.mapper.OfficeMapper;
import com.exadel.sandbox.service.OfficeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("office")
@RequiredArgsConstructor
public class OfficeController {

    private OfficeService officeService;

    @GetMapping("list")
    List<OfficeDto> getOffices() {
        return officeService.getOffices();
    }

    @GetMapping("search")
    OfficeDto getOffice(@RequestParam("id") Long id) {
        return officeService.getById(id);
    }

    @GetMapping("{id}")
    OfficeDto getOfficeByAddressId(@PathVariable("id") Long id) {
        return officeService.getOfficeByAddressId(id);
    }

    @PutMapping()
    OfficeDto createOffice(@RequestBody OfficeDto officeDto) {
        return officeService.create(officeDto);
    }

    @DeleteMapping("{id}")
    void deleteById(@PathVariable("id") Long id) {
        officeService.deleteById(id);
    }

    @PostMapping("{id}")
    OfficeDto updateOffice(@PathVariable("id") Long id, @RequestBody OfficeDto officeDto) {
        return officeService.update(id, officeDto);
    }


}