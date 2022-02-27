package com.exadel.sandbox.seat.service;

import com.exadel.sandbox.base.BaseCrudService;
import com.exadel.sandbox.seat.entity.Seat;
import com.exadel.sandbox.seat.repository.SeatRepository;
import dtos.officeFloor.dto.floorDto.FloorResponseDto;
import dtos.seat.dto.SeatCreateDto;
import dtos.seat.dto.SeatResponseDto;
import dtos.seat.dto.SeatUpdateDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class SeatService extends BaseCrudService<Seat, SeatResponseDto, SeatUpdateDto, SeatCreateDto, SeatRepository> {
    public SeatService(ModelMapper mapper, SeatRepository repository) {
        super(mapper, repository);
    }

    public ResponseEntity<List<FloorResponseDto>> getSeatsByFloorId(Long floorId) {
        return ResponseEntity.ok(mapper.map(repository.findSeatsByFloorId(floorId), new TypeToken<List<FloorResponseDto>>() {
        }.getType()));
    }
}
