package com.exadel.sandbox.parking.controller;

import com.exadel.sandbox.parking.service.ParkingService;
import com.exadel.sandbox.parking.dto.ParkingCreateDto;
import com.exadel.sandbox.parking.dto.ParkingResponseDto;
import com.exadel.sandbox.parking.dto.ParkingUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("parking")
@RequiredArgsConstructor
public class ParkingController {

    private final ParkingService parkingService;


    @GetMapping("list")
    List<ParkingResponseDto> getParkings(){
        return parkingService.getParkings();
    }

    @GetMapping("{id}")
    ParkingResponseDto getParkingById(@PathVariable("id") Long id){
        return parkingService.getParkingById(id);
    }

    @PostMapping()
    ParkingResponseDto createParking(@RequestBody ParkingCreateDto parkingCreateDTO){
        return parkingService.create(parkingCreateDTO);
    }

    @DeleteMapping("{id}")
    void deleteById(@PathVariable("id") Long id){
        parkingService.deleteById(id);
    }

    @PutMapping("{id}")
    ParkingResponseDto updateParking(@PathVariable("id") Long id,
                                     @RequestBody ParkingUpdateDto parkingUpdateDTO){
        return parkingService.update(id, parkingUpdateDTO);
    }

}
