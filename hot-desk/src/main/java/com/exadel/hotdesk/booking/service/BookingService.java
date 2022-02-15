package com.exadel.hotdesk.booking.service;

import com.exadel.hotdesk.booking.dto.BookingCreateDto;
import com.exadel.hotdesk.booking.dto.BookingResponseDto;
import com.exadel.hotdesk.booking.dto.BookingUpdateDto;

import java.util.List;

public interface BookingService {

    List<BookingResponseDto> getBookings();

    BookingResponseDto getBookingById(Long id);

    BookingResponseDto create(BookingCreateDto bookingCreateDto);

    void deleteById(Long id);

    BookingResponseDto update(Long id, BookingUpdateDto bookingUpdateDto);

}
