package com.exadel.hotdesk.parking_spot.controller;

import com.exadel.hotdesk.parking_spot.dto.ParkingSpotCreateDto;
import com.exadel.hotdesk.parking_spot.dto.ParkingSpotResponseDto;
import com.exadel.hotdesk.parking_spot.dto.ParkingSpotUpdateDto;
import com.exadel.hotdesk.parking_spot.service.ParkingSpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("parking-spot")
@RequiredArgsConstructor
public class ParkingSpotController {

    private final ParkingSpotService parkingSpotService;


    @GetMapping("list")
    List<ParkingSpotResponseDto> getParkingSpots(){
        return parkingSpotService.getParkingSpots();
    }

    @GetMapping("{id}")
    ParkingSpotResponseDto getParkingSpotById(@PathVariable("id") Long id){
        return parkingSpotService.getParkingSpotById(id);
    }

    @PostMapping()
    ParkingSpotResponseDto createParkingSpot(@RequestBody ParkingSpotCreateDto parkingSpotCreateDTO){
        return parkingSpotService.create(parkingSpotCreateDTO);
    }

    @DeleteMapping("{id}")
    void deleteById(@PathVariable("id") Long id){
        parkingSpotService.deleteById(id);
    }

    @PutMapping("{id}")
    ParkingSpotResponseDto updateParkingSpot(@PathVariable("id") Long id,
                                         @RequestBody ParkingSpotUpdateDto parkingSpotUpdateDTO){
        return parkingSpotService.update(id, parkingSpotUpdateDTO);
    }

}
