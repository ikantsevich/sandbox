package com.exadel.sandbox.officeFloor.service;

import com.exadel.sandbox.base.BaseCrudService;
import com.exadel.sandbox.officeFloor.dto.officeDto.OfficeCreateDto;
import com.exadel.sandbox.officeFloor.dto.officeDto.OfficeResponseDto;
import com.exadel.sandbox.officeFloor.dto.officeDto.OfficeUpdateDto;
import com.exadel.sandbox.officeFloor.entities.Office;
import com.exadel.sandbox.officeFloor.repositories.OfficeRepository;
import com.exadel.sandbox.seat.dto.SeatResponseDto;
import com.exadel.sandbox.seat.entity.Seat;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

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

    public ResponseEntity<OfficeResponseDto> getOfficeByAddressId(Long id){
        final Office officeByAddressId = repository.findOfficeByAddressId(id);
        final OfficeResponseDto officeResponseDto = mapper.map(officeByAddressId, new TypeToken<OfficeResponseDto>(){}.getType());
        return ResponseEntity.ok(officeResponseDto);
    }
}
