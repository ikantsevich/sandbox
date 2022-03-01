package com.exadel.sandbox.booking.service;

import com.exadel.sandbox.base.BaseCrudService;
import com.exadel.sandbox.booking.dto.booking.BookingCreateDto;
import com.exadel.sandbox.booking.dto.booking.BookingResponseDto;
import com.exadel.sandbox.booking.dto.booking.BookingUpdateDto;
import com.exadel.sandbox.booking.entity.Booking;
import com.exadel.sandbox.booking.entity.BookingDates;
import com.exadel.sandbox.booking.repository.BookingRepository;
import com.exadel.sandbox.exception.DoubleBookingInADayException;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        repository.save(booking);

        return null;
    }

//    For new booking if employee, seat or parking_spot has booked before throws DoubleBookingInADayException
    private void checkNewBooking(Booking booking){
        checkEmployeeBookings(booking);
        checkSeatBookings(booking);
        checkParkingSpotBookings(booking);
    }

//    Why do we need this? Because employee should not do more than one booking in a day.
    private void checkEmployeeBookings(Booking booking){
        List<Booking> bookingsByEmployeeId = repository.findBookingsByEmployeeId(booking.getEmployee().getId());
        checkBookedDatesContainsNewDates(booking, bookingsByEmployeeId);
    }

//    Seat cannot be booked more than one in a day.
    private void checkSeatBookings(Booking booking) {
        List<Booking> bookingsBySeatId = repository.findBookingsBySeatId(booking.getSeat().getId());
        checkBookedDatesContainsNewDates(booking, bookingsBySeatId);
    }

//    Parking spot cannot be booked more than one in a day.
    private void checkParkingSpotBookings(Booking booking){
        List<Booking> bookingsByParkingSpotId = repository.findBookingsByParkingSpotId(booking.getParkingSpot().getId());
        checkBookedDatesContainsNewDates(booking, bookingsByParkingSpotId);
    }

//    Checks if any booking is done for the entity before;
    private void checkBookedDatesContainsNewDates(Booking newBooking, List<Booking> oldBookings){
        List<List<Date>> allBookedDates = oldBookings.stream().map(booking1 -> booking1.getDates().stream().map(BookingDates::getDate).collect(Collectors.toList())).collect(Collectors.toList());

        List<Date> newBookingDates = newBooking.getDates().stream().map(BookingDates::getDate).collect(Collectors.toList());
        allBookedDates.forEach(bookedDates -> {
            newBookingDates.forEach(newBookingDate -> {
                if (bookedDates.contains(newBookingDate))
                    throw new DoubleBookingInADayException("Double Booking in a day not allowed");
            });
        });
    }
}
