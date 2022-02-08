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
    private OfficeMapper mapper;

    @GetMapping("list")
    List<OfficeDto> getOffices(){
        List<Office> offices = officeService.getOffices();
        return offices.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("search")
    OfficeDto getOffice(@RequestParam("id") Long id){
        Office office = officeService.getOfficeByAddressId(id);
        return mapper.toDto(office);
    }

    @GetMapping("{id}")
    OfficeDto getOfficeById(@PathVariable("id") Long id){
        Office office = officeService.getById(id);
        return mapper.toDto(office);
    }

    @PutMapping()
    OfficeDto createOffice(@RequestBody OfficeDto officeDto){
        Office office = mapper.toEntity(officeDto);
        office = officeService.create(office);
        return mapper.toDto(office);
    }

    @DeleteMapping("{id}")
    void deleteById(@PathVariable("id") Long id){
        officeService.deleteById(id);
    }

    @PostMapping("{id}")
    OfficeDto updateOffice(@PathVariable("id") Long id,
                          @RequestBody OfficeDto officeDto ){
        Office office = mapper.toEntity(officeDto);
        office = officeService.update(id, office);
        return mapper.toDto(office);
    }


}
