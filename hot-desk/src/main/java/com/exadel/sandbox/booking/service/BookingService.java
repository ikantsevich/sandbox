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
import com.exadel.sandbox.exception.exceptions.*;
import com.exadel.sandbox.parking_spot.entity.ParkingSpot;
import com.exadel.sandbox.parking_spot.repository.ParkingSpotRepository;
import com.exadel.sandbox.seat.entity.Seat;
import com.exadel.sandbox.seat.repository.SeatRepository;
import com.exadel.sandbox.vacation.entities.Vacation;
import com.exadel.sandbox.vacation.repository.VacationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookingService extends BaseCrudService<Booking, BookingResponseDto, BookingUpdateDto, BookingCreateDto, BookingRepository> {
    private final VacationRepository vacationRepository;
    private final EmployeeRepository employeeRepository;
    private final SeatRepository seatRepository;
    private final ParkingSpotRepository parkingSpotRepository;

    public BookingService(ModelMapper mapper, BookingRepository repository, VacationRepository vacationRepository, EmployeeRepository employeeRepository, SeatRepository seatRepository, ParkingSpotRepository parkingSpotRepository) {
        super(mapper, repository);
        this.vacationRepository = vacationRepository;
        this.employeeRepository = employeeRepository;
        this.seatRepository = seatRepository;
        this.parkingSpotRepository = parkingSpotRepository;
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

        checkParkingSpotAndSeatOffice(booking);

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
        dates.forEach(date -> vacationList.forEach(vacation -> {
            if (vacation.getStart().toLocalDate().isBefore(date) && vacation.getEnd().toLocalDate().isAfter(date))
                throw new VacationOverlapException("On date: " + date + " employee is on vacation");
            if (vacation.getStart().toLocalDate().equals(date) || vacation.getEnd().toLocalDate().equals(date))
                throw new VacationOverlapException("On date: " + date + " employee is on vacation");
        }));
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

    public ResponseEntity<BookingResponseDto> update(Long id, BookingUpdateDto bookingUpdateDTO) {
        Booking booking = checkUpdateBooking(id, bookingUpdateDTO);

        return ResponseEntity.ok(mapper.map(repository.save(booking), BookingResponseDto.class));
    }

//    Checks all possible errors and if bookingUpdateDto passes function returns The booking
    private Booking checkUpdateBooking(Long id, BookingUpdateDto bookingUpdateDto) {
        bookingUpdateDto.setDates(new ArrayList<>(new HashSet<>(bookingUpdateDto.getDates())));

        Booking booking = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Booking with id: " + id + " not found"));

        List<LocalDate> bookingUpdateDtoDates = new ArrayList<>(bookingUpdateDto.getDates());

        List<LocalDate> dates = booking.getDates().stream().map(BookingDates::getDate).collect(Collectors.toList());
        bookingUpdateDtoDates.removeAll(dates);

        checkTheDates(bookingUpdateDtoDates);
        checkParkingSpotAndSeatOffice(booking);

        List<LocalDate> employeeBookedDates = new ArrayList<>();
        List<LocalDate> seatBookingDates = new ArrayList<>();
        List<LocalDate> parkingBookedDates = new ArrayList<>();

//        Checking if employee es free.
        if (bookingUpdateDto.getEmployeeId().equals(booking.getEmployee().getId()))
            employeeBookedDates = repository.checkEmployeeBookedDates(bookingUpdateDto.getEmployeeId(), bookingUpdateDtoDates);
        else
            employeeBookedDates = repository.checkEmployeeBookedDates(bookingUpdateDto.getEmployeeId(), bookingUpdateDto.getDates());

        if (!employeeBookedDates.isEmpty())
            throw new DoubleBookingInADayException("Employee already booked on " + Arrays.toString(employeeBookedDates.toArray()));

//        Checking if seat is free.
        if (bookingUpdateDto.getSeatId().equals(booking.getSeat().getId()))
            seatBookingDates = repository.checkSeatBookingDates(bookingUpdateDto.getSeatId(), bookingUpdateDtoDates);
        else
            seatBookingDates = repository.checkSeatBookingDates(bookingUpdateDto.getSeatId(), bookingUpdateDto.getDates());

        if (!seatBookingDates.isEmpty())
            throw new DoubleBookingInADayException("Seat already booked on " + Arrays.toString(seatBookingDates.toArray()));

//        Checking parking spot is free
        if (bookingUpdateDto.getParkingSpotId() != null) {
            if (booking.getParkingSpot() != null) {
                if (booking.getParkingSpot().getId().equals(bookingUpdateDto.getParkingSpotId()))
                    parkingBookedDates = repository.checkParkingSpotBookedDates(bookingUpdateDto.getParkingSpotId(), bookingUpdateDtoDates);
                else
                    parkingBookedDates = repository.checkParkingSpotBookedDates(id, bookingUpdateDto.getDates());
            }
        }

        if (!parkingBookedDates.isEmpty())
            throw new DoubleBookingInADayException("Parking Spot already booked on " + Arrays.toString(parkingBookedDates.toArray()));

        Employee employee = employeeRepository.findById(bookingUpdateDto.getEmployeeId()).orElseThrow(
                () -> new EntityNotFoundException("Employee with id " + bookingUpdateDto.getEmployeeId() + " not found")
        );

        booking.setEmployee(employee);

        Seat seat = seatRepository.findById(bookingUpdateDto.getSeatId()).orElseThrow(
                () -> new EntityNotFoundException("Seat with id " + bookingUpdateDto.getSeatId() + " not found")
        );

        booking.setSeat(seat);

        if (bookingUpdateDto.getParkingSpotId() != null) {
            ParkingSpot parkingSpot = parkingSpotRepository.findById(bookingUpdateDto.getParkingSpotId()).orElseThrow(
                    () -> new EntityNotFoundException("Parking spot with id " + bookingUpdateDto.getParkingSpotId() + "not found")
            );

            booking.setParkingSpot(parkingSpot);
        }

        booking.setDates(bookingUpdateDto.getDates().stream().map(BookingDates::new).collect(Collectors.toList()));

        return booking;
    }

    public ResponseEntity<BookingResponseDto> cancelBookings(Long id, LocalDate start, LocalDate end) {

        if (end == null)
            end = LocalDate.now().plusMonths(MAX_MONTH);

        if (start.isBefore(LocalDate.now()) || end.isBefore(start))
            throw new DateOutOfBoundException("date range is not correct");

        Booking booking = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Booking with id: " + id + "not found"));

        List<BookingDates> removedDates = new ArrayList<>();

        LocalDate finalEnd = end;

        booking.getDates().forEach(date -> {
            if (date.getDate().equals(start) || date.getDate().equals(finalEnd) || (start.isBefore(date.getDate()) && finalEnd.isAfter(date.getDate())))
                removedDates.add(date);
        });

        booking.getDates().removeAll(removedDates);

        if (booking.getDates().isEmpty())
            repository.delete(booking);

        return ResponseEntity.ok(mapper.map(booking, BookingResponseDto.class));
    }

    public void checkParkingSpotAndSeatOffice(Booking booking){
        if (! booking.getParkingSpot().getOffice().getId().equals(booking.getSeat().getFloor().getOffice().getId()))
            throw new DifferentLocationException("Parking spot and seat should be at the same location");
    }
}
