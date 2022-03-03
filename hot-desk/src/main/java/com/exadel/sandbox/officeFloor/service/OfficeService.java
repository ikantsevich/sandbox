package com.exadel.sandbox.officeFloor.service;

import com.exadel.sandbox.address.dto.AddressResponseDto;
import com.exadel.sandbox.address.repository.AddressRepository;
import com.exadel.sandbox.base.BaseCrudService;
import com.exadel.sandbox.booking.entity.Booking;
import com.exadel.sandbox.exception.EntityNotFoundException;
import com.exadel.sandbox.officeFloor.dto.officeDto.OfficeCreateDto;
import com.exadel.sandbox.officeFloor.dto.officeDto.OfficeResponseDto;
import com.exadel.sandbox.officeFloor.dto.officeDto.OfficeUpdateDto;
import com.exadel.sandbox.officeFloor.entities.Office;
import com.exadel.sandbox.officeFloor.repositories.OfficeRepository;
import com.exadel.sandbox.seat.dto.SeatResponseDto;
import com.exadel.sandbox.seat.entity.Seat;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.xml.sax.EntityResolver;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OfficeService extends BaseCrudService<Office, OfficeResponseDto, OfficeUpdateDto, OfficeCreateDto, OfficeRepository> {
    public OfficeService(ModelMapper mapper, OfficeRepository repository) {
        super(mapper, repository);
    }

    public ResponseEntity<List<SeatResponseDto>> getFreeSeats(Long id, List<LocalDate> dates) {
        List<Seat> freeSeatsByOfficeIdAndDates = repository.findFreeSeatsByOfficeIdAndDates(dates, id);

        List<SeatResponseDto> list = mapper.map(freeSeatsByOfficeIdAndDates, new TypeToken<List<SeatResponseDto>>() {}.getType());
        return ResponseEntity.ok(list);
    }
}
