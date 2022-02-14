package com.exadel.sandbox.booking.service;

import com.exadel.sandbox.booking.dto.BookingCreateDto;
import com.exadel.sandbox.booking.dto.BookingResponseDto;
import com.exadel.sandbox.booking.dto.BookingUpdateDto;

import java.util.List;

public interface BookingService {

    List<BookingResponseDto> getBookings();

    BookingResponseDto getBookingById(Long id);

    BookingResponseDto create(BookingCreateDto bookingCreateDto);

    void deleteById(Long id);

    BookingResponseDto update(Long id, BookingUpdateDto bookingUpdateDto);

}
