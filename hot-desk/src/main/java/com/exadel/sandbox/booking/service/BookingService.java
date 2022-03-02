package com.exadel.sandbox.booking.service;

import com.exadel.sandbox.base.BaseCrudService;
import com.exadel.sandbox.booking.dto.BookingCreateDto;
import com.exadel.sandbox.booking.dto.BookingResponseDto;
import com.exadel.sandbox.booking.dto.BookingUpdateDto;
import com.exadel.sandbox.booking.entity.Booking;
import com.exadel.sandbox.booking.repository.BookingRepository;
import com.exadel.sandbox.exception.DateOutOfBoundException;
import com.exadel.sandbox.exception.DoubleBookingInADayException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookingService extends BaseCrudService<Booking, BookingResponseDto, BookingUpdateDto, BookingCreateDto, BookingRepository> {

    public BookingService(ModelMapper mapper, BookingRepository repository) {
        super(mapper, repository);
    }


    @Override
    public ResponseEntity<BookingResponseDto> create(BookingCreateDto bookingCreateDto) {
        Booking booking = mapper.map(bookingCreateDto, Booking.class);

        checkNewBooking(booking);

        BookingResponseDto bookingResponseDto = mapper.map(repository.save(booking), BookingResponseDto.class);

        return new ResponseEntity<>(bookingResponseDto, HttpStatus.CREATED);
    }

//    Throws exception if any of the objects already booked
    private void checkNewBooking(Booking booking){
        List<Date> dates = booking.getDates().stream().map(date -> mapper.map(date, Date.class)).collect(Collectors.toList());
        System.out.println(Arrays.toString(dates.toArray()));

        checkTheDates(dates);

        if (repository.checkIfSeatIsFree(booking.getSeat().getId(), dates) == 0)
            throwException("Seat " + booking.getSeat().getId());

        if (repository.checkIfEmployeeNotBooked(booking.getEmployee().getId(), dates) == 0)
            throwException("Employee " + booking.getEmployee().getId());

        if (booking.getParkingSpot() != null)
            if (repository.checkIfParkingSpotIsFree(booking.getParkingSpot().getId(), dates) == 0)
                throwException("Parking Spot " + booking.getParkingSpot().getId());
    }

//    Checks if Date is in valid range
    private void checkTheDates(List<Date> dates){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Date today = calendar.getTime();

        calendar.add(Calendar.MONTH, 3);
        Date dateLimit = calendar.getTime();

        dates.forEach(date -> {
            if (date.before(today) || date.after(dateLimit))
                throw new DateOutOfBoundException("Date is not in the range");
        });
    }

//    Throws exception;
    private void throwException(String object){
        throw new DoubleBookingInADayException(object + " already booked on these days");
    }
}
