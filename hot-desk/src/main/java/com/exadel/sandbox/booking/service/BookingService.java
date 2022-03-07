package com.exadel.sandbox.booking.service;

import com.exadel.sandbox.base.BaseCrudService;
import com.exadel.sandbox.booking.dto.BookingCreateDto;
import com.exadel.sandbox.booking.dto.BookingResponseDto;
import com.exadel.sandbox.booking.dto.BookingUpdateDto;
import com.exadel.sandbox.booking.entity.Booking;
import com.exadel.sandbox.booking.entity.BookingDates;
import com.exadel.sandbox.booking.repository.BookingRepository;
import com.exadel.sandbox.exception.exceptions.DateOutOfBoundException;
import com.exadel.sandbox.exception.exceptions.DoubleBookingInADayException;
import com.exadel.sandbox.exception.exceptions.VacationOverlapException;
import com.exadel.sandbox.vacation.entities.Vacation;
import com.exadel.sandbox.vacation.repository.VacationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookingService extends BaseCrudService<Booking, BookingResponseDto, BookingUpdateDto, BookingCreateDto, BookingRepository> {
    private final VacationRepository vacationRepository;

    public BookingService(ModelMapper mapper, BookingRepository repository, VacationRepository vacationRepository) {
        super(mapper, repository);
        this.vacationRepository = vacationRepository;
    }

    public final static int MAX_MONTH = 3;


    @Override
    public ResponseEntity<BookingResponseDto> create(BookingCreateDto bookingCreateDto) {
        Booking booking = mapper.map(bookingCreateDto, Booking.class);

        checkNewBooking(booking);

//        Removing duplicate dates
        booking.setDates(new ArrayList<>(new HashSet<>(booking.getDates())));

        BookingResponseDto bookingResponseDto = mapper.map(repository.save(booking), BookingResponseDto.class);

        return new ResponseEntity<>(bookingResponseDto, HttpStatus.CREATED);
    }

    //    Throws exception if any of the objects already booked
    private void checkNewBooking(Booking booking) {
        List<LocalDate> dates = booking.getDates().stream().map(BookingDates::getDate).collect(Collectors.toList());

        checkTheDates(dates);
        checkTheEmployeesVacation(dates, booking.getEmployee().getId());

        List<LocalDate> seatsBookedDates = repository.checkSeatBookingDates(booking.getSeat().getId(), dates);
        List<LocalDate> employeeBookedDates = repository.checkEmployeeBookedDates(booking.getEmployee().getId(), dates);


        if (!seatsBookedDates.isEmpty())
            throw new DoubleBookingInADayException("Seat already booked on " + Arrays.toString(seatsBookedDates.toArray()));

        if (!employeeBookedDates.isEmpty())
            throw new DoubleBookingInADayException("Employee already booked on " + Arrays.toString(employeeBookedDates.toArray()));

        if (booking.getParkingSpot() != null) {
            List<LocalDate> parkingBooedDates = repository.checkParkingSpotBookedDates(booking.getParkingSpot().getId(), dates);
            if (!parkingBooedDates.isEmpty())
                throw new DoubleBookingInADayException("Parking Spot already booked on " + Arrays.toString(parkingBooedDates.toArray()));
        }
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

    private void checkTheEmployeesVacation(List<LocalDate> dates, Long employeeId) {
        List<Vacation> vacationList = vacationRepository.findAllByEmployeeId(employeeId);
         dates.forEach(date -> {
             vacationList.forEach(vacation -> {
                 if (vacation.getStart().toLocalDate().isBefore(date) && vacation.getEnd().toLocalDate().isAfter(date))
                     throw new VacationOverlapException("On date: " + date + " employee is on vacation");
                 if (vacation.getStart().toLocalDate().equals(date) || vacation.getEnd().toLocalDate().equals(date))
                     throw new VacationOverlapException("On date: " + date + " employee is on vacation");
             });
         });
    }

    public ResponseEntity<List<BookingResponseDto>> getCurrentBookings() {
        LocalDate today = LocalDate.now();
        LocalDate limitDates = today.plusMonths(MAX_MONTH);

        List<Booking> bookingsInTimePeriod = repository.findBookingsInTimePeriod(today, limitDates);

        List<BookingResponseDto> bookingResponseDtos = bookingsInTimePeriod.stream().map(booking -> mapper.map(booking, BookingResponseDto.class)).collect(Collectors.toList());

        return ResponseEntity.ok(bookingResponseDtos);
    }

    public ResponseEntity<List<BookingResponseDto>> getBookingsByOfficeId(Long officeId) {
        List<Booking> bookingsByOfficeId = repository.findBookingsByOfficeId(officeId);

        List<BookingResponseDto> bookingResponseDtos = bookingsByOfficeId.stream().map(booking -> mapper.map(booking, BookingResponseDto.class)).collect(Collectors.toList());

        return ResponseEntity.ok(bookingResponseDtos);
    }
}
