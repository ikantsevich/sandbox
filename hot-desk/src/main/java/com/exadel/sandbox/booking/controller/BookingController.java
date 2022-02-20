package com.exadel.sandbox.booking.controller;

import com.exadel.sandbox.booking.dto.BookingCreateDto;
import com.exadel.sandbox.booking.dto.BookingResponseDto;
import com.exadel.sandbox.booking.dto.BookingUpdateDto;
import com.exadel.sandbox.booking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;


    @GetMapping("list")
    List<BookingResponseDto> getBookings() {
        return bookingService.getBookings();
    }

    @GetMapping("{id}")
    BookingResponseDto getBookingById(@PathVariable("id") Long id) {
        return bookingService.getBookingById(id);
    }

    @PostMapping()
    BookingResponseDto createBooking(@RequestBody BookingCreateDto bookingCreateDTO) {
        return bookingService.create(bookingCreateDTO);
    }

    @DeleteMapping("{id}")
    void deleteById(@PathVariable("id") Long id) {
        bookingService.deleteById(id);
    }

    @PutMapping("{id}")
    BookingResponseDto updateBooking(@PathVariable("id") Long id,
                                     @RequestBody BookingUpdateDto bookingUpdateDTO) {
        return bookingService.update(id, bookingUpdateDTO);
    }

}
