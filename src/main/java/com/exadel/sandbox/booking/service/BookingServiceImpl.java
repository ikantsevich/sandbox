package com.exadel.sandbox.booking.service;

import com.exadel.sandbox.address.dto.AddressResponseDto;
import com.exadel.sandbox.address.entity.Address;
import com.exadel.sandbox.booking.dto.BookingCreateDto;
import com.exadel.sandbox.booking.dto.BookingResponseDto;
import com.exadel.sandbox.booking.dto.BookingUpdateDto;
import com.exadel.sandbox.booking.entity.Booking;
import com.exadel.sandbox.booking.repository.BookingRepository;
import com.exadel.sandbox.employee.dto.employeeDto.EmployeeResponseDto;
import com.exadel.sandbox.employee.entity.Employee;
import com.exadel.sandbox.exception.EntityNotFoundException;
import com.exadel.sandbox.parking_spot.dto.ParkingSpotResponseDto;
import com.exadel.sandbox.parking_spot.entity.ParkingSpot;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final ModelMapper mapper;

    @Override
    public List<BookingResponseDto> getBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        List<BookingResponseDto> bookingResponseDtos = new ArrayList<>();
        for (Booking booking : bookings) {
            bookingResponseDtos.add(fullMap(booking));
        }
        return bookingResponseDtos;
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
        Booking booking = bookingRepository.findById(id).orElseThrow(
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

        ParkingSpotResponseDto parkingSpotResponseDto = null;
        if (booking.getParkingSpot() != null)
            parkingSpotResponseDto = mapper.map(booking.getParkingSpot(), ParkingSpotResponseDto.class);

        bookingResponseDto.setParkingSpotResponseDto(parkingSpotResponseDto);

        EmployeeResponseDto employeeResponseDto = null;
        if(booking.getEmployee() != null){
            employeeResponseDto = mapper.map(booking.getEmployee(), EmployeeResponseDto.class);
        }

        bookingResponseDto.setEmployeeResponseDto(employeeResponseDto);

        return bookingResponseDto;
    }
}
