package com.exadel.sandbox.booking.service;

import com.exadel.sandbox.base.BaseCrudService;
import com.exadel.sandbox.booking.dto.BookingCreateDto;
import com.exadel.sandbox.booking.dto.BookingResponseDto;
import com.exadel.sandbox.booking.dto.BookingUpdateDto;
import com.exadel.sandbox.booking.entity.Booking;
import com.exadel.sandbox.booking.entity.BookingDates;
import com.exadel.sandbox.booking.repository.BookingRepository;
import com.exadel.sandbox.employee.entity.Employee;
import com.exadel.sandbox.employee.repository.EmployeeRepository;
import com.exadel.sandbox.exception.exceptions.DateOutOfBoundException;
import com.exadel.sandbox.exception.exceptions.DoubleBookingInADayException;
import com.exadel.sandbox.exception.exceptions.EntityNotFoundException;
import com.exadel.sandbox.exception.exceptions.ForbiddenException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookingService extends BaseCrudService<Booking, BookingResponseDto, BookingUpdateDto, BookingCreateDto, BookingRepository> {

    private final EmployeeRepository employeeRepository;
    public BookingService(ModelMapper mapper, BookingRepository repository, EmployeeRepository employeeRepository) {
        super(mapper, repository);
        this.employeeRepository = employeeRepository;
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

    public ResponseEntity<BookingResponseDto> getById(Long id, Principal principal) {
        ResponseEntity<BookingResponseDto> byId = super.getById(id);
        checkForPrivacy(Objects.requireNonNull(byId.getBody()).getEmployeeId(), principal);
        return byId;
    }

    public ResponseEntity<BookingResponseDto> create(BookingCreateDto bookingCreateDTO, Principal principal) {
        checkForPrivacy(bookingCreateDTO.getEmployeeId(), principal);
        return super.create(bookingCreateDTO);
    }

    public void delete(Long id, Principal principal) {
        checkForPrivacy(id, principal);
        super.delete(id);
    }

    public ResponseEntity<BookingResponseDto> update(Long id, BookingUpdateDto bookingUpdateDTO, Principal principal) {
        Booking booking = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Booking with id: " + id + " not found"));

        checkForPrivacy(bookingUpdateDTO.getEmployeeId(), principal);
        checkForPrivacy(booking.getEmployee().getId(), principal);

        return update(id, bookingUpdateDTO);
    }

    @Override
    public ResponseEntity<BookingResponseDto> update(Long id, BookingUpdateDto bookingUpdateDto) {
        Booking booking = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Booking with id: " + id + " not found"));
        Booking newBooking = mapper.map(mapper.map(bookingUpdateDto, BookingCreateDto.class), Booking.class);

        checkNewBooking(newBooking);

        booking.setEmployee(newBooking.getEmployee());
        booking.setSeat(newBooking.getSeat());
        booking.setParkingSpot(newBooking.getParkingSpot());
        booking.setDates(newBooking.getDates());

        return ResponseEntity.ok(mapper.map(repository.save(booking), BookingResponseDto.class));
    }

    //        Checking that employee can't see other employee's bookings
    private void checkForPrivacy(Long id, Principal principal){
        Employee employee = employeeRepository.findEmployeeByEmail(principal.getName()).orElseThrow((() -> new EntityNotFoundException("You are not our employee")));

        if (!employee.getId().equals(id))
            employee.getRoles().forEach(role -> {
                if (role.getName().equals("ROLE_EMPLOYEE"))
                    throw new ForbiddenException();
            });
    }

}
