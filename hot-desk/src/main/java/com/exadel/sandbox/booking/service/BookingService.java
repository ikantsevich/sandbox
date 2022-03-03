package com.exadel.sandbox.booking.service;

import com.exadel.sandbox.base.BaseCrudService;
import com.exadel.sandbox.booking.dto.BookingCreateDto;
import com.exadel.sandbox.booking.dto.BookingResponseDto;
import com.exadel.sandbox.booking.dto.BookingUpdateDto;
import com.exadel.sandbox.booking.entity.Booking;
import com.exadel.sandbox.booking.entity.BookingDates;
import com.exadel.sandbox.booking.repository.BookingRepository;
import com.exadel.sandbox.exception.DateOutOfBoundException;
import com.exadel.sandbox.exception.DoubleBookingInADayException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookingService extends BaseCrudService<Booking, BookingResponseDto, BookingUpdateDto, BookingCreateDto, BookingRepository> {

    public BookingService(ModelMapper mapper, BookingRepository repository) {
        super(mapper, repository);
    }
    public final static int MAX_MONTH = 3;


    @Override
    public ResponseEntity<BookingResponseDto> create(BookingCreateDto bookingCreateDto) {
        Booking booking = mapper.map(bookingCreateDto, Booking.class);

        checkNewBooking(booking);

        BookingResponseDto bookingResponseDto = mapper.map(repository.save(booking), BookingResponseDto.class);

        return new ResponseEntity<>(bookingResponseDto, HttpStatus.CREATED);
    }

    //    Throws exception if any of the objects already booked
    private void checkNewBooking(Booking booking) {
        List<LocalDate> dates = booking.getDates().stream().map(BookingDates::getDate).collect(Collectors.toList());

        checkTheDates(dates);

        Integer integer = repository.checkIfSeatIsFree(booking.getSeat().getId(), dates);
        if (integer == 0)
            throwException("Seat " + booking.getSeat().getId());

        if (repository.checkIfEmployeeNotBooked(booking.getEmployee().getId(), dates) == 0)
            throwException("Employee " + booking.getEmployee().getId());

        if (booking.getParkingSpot() != null)
            if (repository.checkIfParkingSpotIsFree(booking.getParkingSpot().getId(), dates) == 0)
                throwException("Parking Spot " + booking.getParkingSpot().getId());
    }

    //    Checks if Date is in valid range
    private void checkTheDates(List<LocalDate> dates) {
        LocalDate today = LocalDate.now();

        LocalDate dateLimit = today.plusMonths(MAX_MONTH);

        dates.forEach(date -> {
            if (!(date.isAfter(today) && date.isBefore(dateLimit)) && !(date.equals(today) || date.equals(dateLimit)))
                throw new DateOutOfBoundException("Date " + date + " is not in the range ");
        });
    }

    //    Throws exception;
    private void throwException(String object) {
        throw new DoubleBookingInADayException(object + " already booked on these days");
    }

    public ResponseEntity<List<BookingResponseDto>> getCurrentBookings() {
        LocalDate today = LocalDate.now();
        LocalDate limitDates = today.plusMonths(MAX_MONTH);

        List<Booking> bookingsInTimePeriod = repository.findBookingsInTimePeriod(today, limitDates);

        return ResponseEntity.ok(mapper.map(bookingsInTimePeriod, new TypeToken<List<BookingResponseDto>>(){}.getType()));
    }

    public ResponseEntity<List<BookingResponseDto>> getBookingsByOfficeId(Long officeId) {
        List<Booking> bookingsByOfficeId = repository.findBookingsByOfficeId(officeId);

        return ResponseEntity.ok(mapper.map(bookingsByOfficeId, new TypeToken<List<BookingResponseDto>>(){}.getType()));
    }
}
