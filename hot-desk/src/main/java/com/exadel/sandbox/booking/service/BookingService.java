package com.exadel.sandbox.booking.service;

import com.exadel.sandbox.base.BaseCrudService;
import com.exadel.sandbox.booking.entity.Booking;
import com.exadel.sandbox.booking.repository.BookingRepository;
import dtos.booking.dto.BookingCreateDto;
import dtos.booking.dto.BookingResponseDto;
import dtos.booking.dto.BookingUpdateDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookingService extends BaseCrudService<Booking, BookingResponseDto, BookingUpdateDto, BookingCreateDto, BookingRepository> {

    public BookingService(ModelMapper mapper, BookingRepository repository) {
        super(mapper, repository);
    }
}
