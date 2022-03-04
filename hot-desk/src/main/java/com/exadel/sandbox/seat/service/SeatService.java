package com.exadel.sandbox.seat.service;

import com.exadel.sandbox.base.BaseCrudService;
import com.exadel.sandbox.booking.service.BookingService;
import com.exadel.sandbox.officeFloor.dto.floorDto.FloorResponseDto;
import com.exadel.sandbox.seat.dto.SeatCreateDto;
import com.exadel.sandbox.seat.dto.SeatResponseDto;
import com.exadel.sandbox.seat.dto.SeatUpdateDto;
import com.exadel.sandbox.seat.entity.Seat;
import com.exadel.sandbox.seat.repository.SeatRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Component
@Transactional
public class SeatService extends BaseCrudService<Seat, SeatResponseDto, SeatUpdateDto, SeatCreateDto, SeatRepository> {
    public SeatService(ModelMapper mapper, SeatRepository repository) {
        super(mapper, repository);
    }

    public ResponseEntity<List<FloorResponseDto>> getSeatsByFloorId(Long floorId) {
        return ResponseEntity.ok(mapper.map(repository.findSeatsByFloorId(floorId), new TypeToken<List<FloorResponseDto>>() {}.getType()));
    }

    public ResponseEntity<List<LocalDate>> getSeatsBookedDatesById(Long id) {
        LocalDate today = LocalDate.now();
        LocalDate timeLimit = today.plusMonths(BookingService.MAX_MONTH);
        List<LocalDate> seatsBookedDates = repository.findSeatsBookedDates(id, today, timeLimit);
        return ResponseEntity.ok(seatsBookedDates);
    }
}
