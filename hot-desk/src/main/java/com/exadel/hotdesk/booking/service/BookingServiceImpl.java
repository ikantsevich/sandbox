package com.exadel.hotdesk.booking.service;

import com.exadel.hotdesk.booking.dto.BookingCreateDto;
import com.exadel.hotdesk.booking.dto.BookingResponseDto;
import com.exadel.hotdesk.booking.dto.BookingUpdateDto;
import com.exadel.hotdesk.booking.entity.Booking;
import com.exadel.hotdesk.employee.dto.employeeDto.EmployeeResponseDto;
import com.exadel.hotdesk.exception.EntityNotFoundException;
import com.exadel.hotdesk.parking_spot.dto.ParkingSpotResponseDto;
import com.exadel.hotdesk.parking_spot.entity.ParkingSpot;
import com.exadel.hotdesk.booking.repository.BookingRepository;
import com.exadel.hotdesk.employee.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final ModelMapper mapper;

    @Override
    public List<BookingResponseDto> getBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream()
                .map(this::fullMap)
                .collect(Collectors.toList());
    }

    @Override
    public BookingResponseDto getBookingById(Long id) {
        Optional<Booking> bookingByID = bookingRepository.findById(id);
        Booking booking = bookingByID.orElseThrow(
                () -> new EntityNotFoundException("Can\'t get Booking with ID: " + id + ". Doesn\'t exist."));
        return fullMap(booking);
    }

    @Override
    public BookingResponseDto create(BookingCreateDto bookingCreateDto) {
        Booking booking = mapper.map(bookingCreateDto, Booking.class);

        if(bookingCreateDto.getParkingSpotResponseDto() != null)
            booking.setParkingSpot(mapper.map(bookingCreateDto.getParkingSpotResponseDto(), ParkingSpot.class));

        if(bookingCreateDto.getEmployeeResponseDto() != null)
            booking.setEmployee(mapper.map(bookingCreateDto.getEmployeeResponseDto(), Employee.class));

        return fullMap(bookingRepository.save(booking));
    }

    @Override
    public void deleteById(Long id) {
        bookingRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can\'t delete Booking with ID: " + id + ". Doesn\'t exist."));
        bookingRepository.deleteById(id);
    }

    @Override
    public BookingResponseDto update(Long id, BookingUpdateDto bookingUpdateDto) {
        Booking booking = bookingRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can\'t update Booking with ID: " + id + ". Doesn\'t exist."));
        mapper.map(bookingUpdateDto, booking);
        return mapper.map(bookingRepository.save(booking), BookingResponseDto.class);
    }

    private BookingResponseDto fullMap(Booking booking) {
        BookingResponseDto bookingResponseDto = mapper.map(booking, BookingResponseDto.class);

        if (booking.getParkingSpot() != null) {
            bookingResponseDto.setParkingSpotResponseDto(mapper.map(booking.getParkingSpot(), ParkingSpotResponseDto.class));
        }

        if (booking.getEmployee() != null) {
            bookingResponseDto.setEmployeeResponseDto(mapper.map(booking.getEmployee(), EmployeeResponseDto.class));
        }

        return bookingResponseDto;
    }
}
