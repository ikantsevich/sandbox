package com.exadel.sandbox.notification.aspect;

import com.exadel.sandbox.address.entity.Address;
import com.exadel.sandbox.booking.dto.BookingResponseDto;
import com.exadel.sandbox.booking.entity.Booking;
import com.exadel.sandbox.booking.entity.BookingDates;
import com.exadel.sandbox.booking.repository.BookingRepository;
import com.exadel.sandbox.booking.service.BookingService;
import com.exadel.sandbox.exception.exceptions.EntityNotFoundException;
import com.exadel.sandbox.notification.dto.NotificationCreateDto;
import com.exadel.sandbox.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Aspect
@Configuration
@RequiredArgsConstructor
public class BookingAspects {
    private final NotificationService notificationService;
    private final BookingRepository bookingRepository;


    @AfterReturning(value = "execution(* com.exadel.sandbox.booking.repository.BookingRepository.save(..))", returning = "booking")
    public void sendWhenCreate(JoinPoint joinPoint, Booking booking) {
        NotificationCreateDto notification = new NotificationCreateDto(booking.getEmployee().getId());

        notification.setTitle("Congrats, your workplace booking is confirmed");

        Address address = booking.getSeat().getFloor().getOffice().getAddress();
        String officeName = address.getCountry() + " " + address.getCity() + " " + address.getStreet() + " " + address.getBuildingNum();

        String message = """
                Office name: %s
                                
                Floor number: %s
                                
                Seat number: %s
                                
                Dates: %s
                Booking number: %s
                Executed: %s
                                
                """.formatted(
                officeName,
                booking.getSeat().getFloor().getFloorNum(),
                booking.getSeat().getNumber(),
                Arrays.toString(booking.getDates().stream().map(BookingDates::getDate).toArray()),
                booking.getId(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );

        notification.setMessage(message);

        notificationService.send(notification);
    }

    @AfterReturning(value = "execution(* com.exadel.sandbox.booking.service.BookingService.cancelBookings(..))", returning = "bookingResponseDtoResponseEntity")
    public void sendWhenDelete(JoinPoint joinPoint, ResponseEntity<BookingResponseDto> bookingResponseDtoResponseEntity) {
        BookingResponseDto bookingResponseDto = bookingResponseDtoResponseEntity.getBody();

        Long id = (Long) joinPoint.getArgs()[0];
        LocalDate start = (LocalDate) joinPoint.getArgs()[1];
        LocalDate end = (LocalDate) joinPoint.getArgs()[2];

        assert bookingResponseDto != null;
        NotificationCreateDto notification = new NotificationCreateDto(bookingResponseDto.getEmployeeId());

        if (end == null)
            end = LocalDate.now().plusMonths(BookingService.MAX_MONTH);

        Booking booking = bookingRepository.findById(id).orElse(null);

        if (booking == null){
            notification.setMessage("""
                    booking: %s
                    
                    Executed: %s
                    """.formatted(bookingResponseDto.getId(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));

            notification.setTitle("Booking cancelled successfully");

            notificationService.send(notification);
            return;
        }

        Address address = booking.getSeat().getFloor().getOffice().getAddress();
        String officeName = address.getCountry() + " " + address.getCity() + " " + address.getStreet() + " " + address.getBuildingNum();


        notification.setTitle("Your Booking cancelled successfully");

        String message = """
                Booking number: %s
                Office name: %s
                Seat number: %s
                Floor number: %s
                                
                Cancelled reservation: %s
                                
                Executed: %s
                """.formatted(
                booking.getId(),
                officeName,
                booking.getSeat().getNumber(),
                booking.getSeat().getFloor().getFloorNum(),
                start + " - " + end,
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );

        notification.setMessage(message);

        notificationService.send(notification);
    }
}