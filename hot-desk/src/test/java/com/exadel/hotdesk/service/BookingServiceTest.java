package com.exadel.hotdesk.service;

import com.exadel.sandbox.booking.dto.BookingCreateDto;
import com.exadel.sandbox.booking.dto.BookingResponseDto;
import com.exadel.sandbox.booking.dto.BookingUpdateDto;
import com.exadel.sandbox.booking.entity.Booking;
import com.exadel.sandbox.booking.entity.BookingDates;
import com.exadel.sandbox.booking.repository.BookingRepository;
import com.exadel.sandbox.booking.service.BookingService;
import com.exadel.sandbox.employee.entity.Employee;
import com.exadel.sandbox.employee.repository.EmployeeRepository;
import com.exadel.sandbox.exception.exceptions.DoubleBookingInADayException;
import com.exadel.sandbox.exception.exceptions.VacationOverlapException;
import com.exadel.sandbox.parking_spot.entity.ParkingSpot;
import com.exadel.sandbox.seat.entity.Seat;
import com.exadel.sandbox.seat.repository.SeatRepository;
import com.exadel.sandbox.vacation.entities.Vacation;
import com.exadel.sandbox.vacation.repository.VacationRepository;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {


    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private VacationRepository vacationRepository;
    @Mock
    private SeatRepository seatRepository;
    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        bookingService = new BookingService(mapper, bookingRepository, vacationRepository, employeeRepository, seatRepository, null);
    }

    @Test
    void createBookingSuccessfulTest() {
        List<LocalDate> dates = Arrays.asList(LocalDate.now().plusDays(10), LocalDate.now().plusDays(20));
        List<BookingDates> datesList = Arrays.asList(new BookingDates(LocalDate.now().plusDays(10)),
                                                     new BookingDates(LocalDate.now().plusDays(20)));
        BookingCreateDto bookingCreateDto = new BookingCreateDto(1L, dates);
        Seat seat = new Seat(1L, null, null, null, 1, null, null, null, null, null);
        Employee employee = new Employee(1L, null, null, null, null, null, null, null, null, null, null, null, null);
        Booking booking = new Booking(1L, employee, seat, null, datesList, null, null);
        List<Vacation> vacations = Arrays.asList(new Vacation(null, LocalDateTime.now(), LocalDateTime.now(), null, null, null));

        given(vacationRepository.findAllByEmployeeId(1L)).willReturn(vacations);
        given(bookingRepository.checkSeatBookingDates(1L, dates)).willReturn(new ArrayList<>());
        given(bookingRepository.checkEmployeeBookedDates(1L, dates)).willReturn(new ArrayList<>());
        given(mapper.map(bookingCreateDto, Booking.class)).willReturn(booking);

        ResponseEntity<BookingResponseDto> b = bookingService.create(bookingCreateDto);
        assertThat(b).isNotNull();
        verify(bookingRepository).save(any(Booking.class));
    }

    @Test
    void createBookingWhenDatesBookedTest() {
        List<LocalDate> dates = Arrays.asList(LocalDate.now().plusDays(10), LocalDate.now().plusDays(20));
        List<BookingDates> datesList = Arrays.asList(new BookingDates(LocalDate.now().plusDays(10)),
                                                     new BookingDates(LocalDate.now().plusDays(20)));
        BookingCreateDto bookingCreateDto = new BookingCreateDto(1L, dates);
        Seat seat = new Seat(1L, null, null, null, 1, null, null, null, null, null);
        Employee employee = new Employee(1L, null, null, null, null, null, null, null, null, null, null, null, null);
        Booking booking = new Booking(1L, employee, seat, null, datesList, null, null);
        List<Vacation> vacations = List.of(new Vacation(null, LocalDateTime.now(), LocalDateTime.now(), null, null, null));

        given(vacationRepository.findAllByEmployeeId(1L)).willReturn(vacations);
        given(bookingRepository.checkSeatBookingDates(1L, dates)).willReturn(dates);
        given(bookingRepository.checkEmployeeBookedDates(1L, dates)).willReturn(new ArrayList<>());
        given(mapper.map(bookingCreateDto, Booking.class)).willReturn(booking);

        assertThatThrownBy(() -> bookingService.create(bookingCreateDto))
                .isInstanceOf(DoubleBookingInADayException.class)
                .hasMessageContaining("Seat already booked on " + Arrays.toString(dates.toArray()));
    }

    @Test
    void createBookingWhenDatesBookedByOtherEmployeeTest() {
        List<LocalDate> dates = Arrays.asList(LocalDate.now().plusDays(10), LocalDate.now().plusDays(20));
        List<BookingDates> datesList = Arrays.asList(new BookingDates(LocalDate.now().plusDays(10)),
                                                     new BookingDates(LocalDate.now().plusDays(20)));
        BookingCreateDto bookingCreateDto = new BookingCreateDto(1L, dates);
        Seat seat = new Seat(1L, null, null, null, 1, null, null, null, null, null);
        Employee employee = new Employee(1L, null, null, null, null, null, null, null, null, null, null, null, null);
        Booking booking = new Booking(1L, employee, seat, null, datesList, null, null);
        List<Vacation> vacations = List.of(new Vacation(null, LocalDateTime.now(), LocalDateTime.now(), null, null, null));

        given(vacationRepository.findAllByEmployeeId(1L)).willReturn(vacations);
        given(bookingRepository.checkSeatBookingDates(1L, dates)).willReturn(new ArrayList<>());
        given(bookingRepository.checkEmployeeBookedDates(1L, dates)).willReturn(dates);
        given(mapper.map(bookingCreateDto, Booking.class)).willReturn(booking);

        assertThatThrownBy(() -> bookingService.create(bookingCreateDto))
                .isInstanceOf(DoubleBookingInADayException.class)
                .hasMessageContaining("Employee already booked on " + Arrays.toString(dates.toArray()));
    }

    @Test
    void createBookingWhenParkingSpotBookedTest() {
        List<LocalDate> dates = Arrays.asList(LocalDate.now().plusDays(10), LocalDate.now().plusDays(20));
        List<BookingDates> datesList = Arrays.asList(new BookingDates(LocalDate.now().plusDays(10)),
                                                     new BookingDates(LocalDate.now().plusDays(20)));
        BookingCreateDto bookingCreateDto = new BookingCreateDto(1L, dates);
        ParkingSpot parkingSpot = new ParkingSpot(1L, null, null, null, null, null, null, null);
        Seat seat = new Seat(1L, null, null, null, 1, null, null, null, null, null);
        Employee employee = new Employee(1L, null, null, null, null, null, null, null, null, null, null, null, null);
        Booking booking = new Booking(1L, employee, seat, parkingSpot, datesList, null, null);
        List<Vacation> vacations = List.of(new Vacation(null, LocalDateTime.now(), LocalDateTime.now(), null, null, null));

        given(vacationRepository.findAllByEmployeeId(1L)).willReturn(vacations);
        given(bookingRepository.checkSeatBookingDates(1L, dates)).willReturn(new ArrayList<>());
        given(bookingRepository.checkEmployeeBookedDates(1L, dates)).willReturn(new ArrayList<>());
        given(bookingRepository.checkParkingSpotBookedDates(1L, dates)).willReturn(dates);
        given(mapper.map(bookingCreateDto, Booking.class)).willReturn(booking);

        assertThatThrownBy(() -> bookingService.create(bookingCreateDto))
                .isInstanceOf(DoubleBookingInADayException.class)
                .hasMessageContaining("Parking Spot already booked on " + Arrays.toString(dates.toArray()));
    }

    @Test
    void createBookingWhenVacationOverlapsTest() {
        List<LocalDate> dates = Arrays.asList(LocalDate.now().plusDays(10), LocalDate.now().plusDays(20));
        List<BookingDates> datesList = Arrays.asList(new BookingDates(LocalDate.now().plusDays(10)),
                                                     new BookingDates(LocalDate.now().plusDays(20)));
        BookingCreateDto bookingCreateDto = new BookingCreateDto(1L, dates);
        Seat seat = new Seat(1L, null, null, null, 1, null, null, null, null, null);
        Employee employee = new Employee(1L, null, null, null, null, null, null, null, null, null, null, null, null);
        Booking booking = new Booking(1L, employee, seat, null, datesList, null, null);
        List<Vacation> vacations = List.of(new Vacation(null, LocalDateTime.now().plusDays(10), LocalDateTime.now().plusDays(20), null, null, null));

        given(vacationRepository.findAllByEmployeeId(1L)).willReturn(vacations);
        given(mapper.map(bookingCreateDto, Booking.class)).willReturn(booking);

        assertThatThrownBy(() -> bookingService.create(bookingCreateDto))
                .isInstanceOf(VacationOverlapException.class)
                .hasMessageContaining("On date: " + LocalDate.now().plusDays(10) + " employee is on vacation");
    }

    @Test
    void getCurrentBookingsSuccessTest() {
        LocalDate now = LocalDate.now();
        LocalDate limit = LocalDate.now().plusMonths(3);
        List<Booking> bookings = Arrays.asList(new Booking(), new Booking());

        given(bookingRepository.findBookingsInTimePeriod(now, limit)).willReturn(bookings);

        ResponseEntity<List<BookingResponseDto>> currBookings =  bookingService.getCurrentBookings();
        assertThat(currBookings).isNotNull();
    }

    @Test
    void getBookingsByOfficeIdSuccessTest() {
        List<Booking> bookings = Arrays.asList(new Booking(), new Booking());
        given(bookingRepository.findBookingsByOfficeId(1L)).willReturn(bookings);
        ResponseEntity<List<BookingResponseDto>> currBookings =  bookingService.getBookingsByOfficeId(1L);
        assertThat(currBookings).isNotNull();
    }

    @Test
    void getBookingsByEmIdSuccessTest() {
        ResponseEntity<List<BookingResponseDto>> currBookings =  bookingService.getBookingsByEmId(15654L);
        assertThat(currBookings).isNotNull();
    }

    @Test
    void updateBookingSuccessTest() {
        List<LocalDate> updateDates = Arrays.asList(LocalDate.now().plusDays(12), LocalDate.now().plusDays(11));
        List<BookingDates> datesList = Arrays.asList(new BookingDates(LocalDate.now().plusDays(10)),
                                                     new BookingDates(LocalDate.now().plusDays(20)));
        BookingUpdateDto bookingUpdateDto = new BookingUpdateDto(null, updateDates);
        bookingUpdateDto.setSeatId(1L);
        bookingUpdateDto.setEmployeeId(1L);
        Seat seat = new Seat(1L, null, null, null, 1, null, null, null, null, null);
        Employee employee = new Employee(1L, null, null, null, null, null, null, null, null, null, null, null, null);
        Booking booking = new Booking(1L, employee, seat, null, datesList, null, null);

        given(bookingRepository.checkSeatBookingDates(1L, updateDates)).willReturn(new ArrayList<>());
        given(bookingRepository.checkEmployeeBookedDates(1L, updateDates)).willReturn(new ArrayList<>());
        given(bookingRepository.findById(1L)).willReturn(Optional.of(booking));
        given(employeeRepository.findById(1L)).willReturn(Optional.of(employee));
        given(seatRepository.findById(1L)).willReturn(Optional.of(seat));
        given(bookingRepository.save(booking)).willReturn(booking);
        given(mapper.map(booking, BookingResponseDto.class)).willReturn(new BookingResponseDto());

        ResponseEntity<BookingResponseDto> b = bookingService.update(1L, bookingUpdateDto);

        assertThat(b).isNotNull();
        verify(bookingRepository).save(any(Booking.class));
    }

    @Test
    void cancelBookingSuccessTest(){
        List<LocalDate> dates = Arrays.asList(LocalDate.now().plusDays(10), LocalDate.now().plusDays(20));
        List<BookingDates> datesList = Arrays.asList(new BookingDates(LocalDate.now().plusDays(10)),
                new BookingDates(LocalDate.now().plusDays(20)));
        Seat seat = new Seat(1L, null, null, null, 1, null, null, null, null, null);
        Employee employee = new Employee(1L, null, null, null, null, null, null, null, null, null, null, null, null);
        Booking booking = new Booking(1L, employee, seat, null, datesList, null, null);

        given(bookingRepository.findById(1L)).willReturn(Optional.of(booking));

        ResponseEntity<BookingResponseDto> b = bookingService.cancelBookings(1L, LocalDate.now().plusDays(9), LocalDate.now().plusDays(21));
        assertThat(b).isNotNull();
        verify(bookingRepository).delete(any(Booking.class));
    }
}