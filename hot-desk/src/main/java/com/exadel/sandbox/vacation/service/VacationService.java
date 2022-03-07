package com.exadel.sandbox.vacation.service;

import com.exadel.sandbox.base.BaseCrudService;
import com.exadel.sandbox.booking.entity.Booking;
import com.exadel.sandbox.booking.entity.BookingDates;
import com.exadel.sandbox.booking.repository.BookingRepository;
import com.exadel.sandbox.exception.exceptions.DateOutOfBoundException;
import com.exadel.sandbox.exception.exceptions.VacationOverlapException;
import com.exadel.sandbox.vacation.dto.VacationCreateDto;
import com.exadel.sandbox.vacation.dto.VacationResponseDto;
import com.exadel.sandbox.vacation.dto.VacationUpdateDto;
import com.exadel.sandbox.vacation.entities.Vacation;
import com.exadel.sandbox.vacation.repository.VacationRepository;
import org.apache.commons.collections.IteratorUtils;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Transactional
public class VacationService extends BaseCrudService<Vacation, VacationResponseDto, VacationUpdateDto, VacationCreateDto, VacationRepository> {
    private final BookingRepository bookingRepository;

    public VacationService(ModelMapper mapper, VacationRepository repository, BookingRepository bookingRepository) {
        super(mapper, repository);
        this.bookingRepository = bookingRepository;
    }

    @Override
    public ResponseEntity<VacationResponseDto> create(VacationCreateDto vacationCreateDto) {
        Vacation vacation = mapper.map(vacationCreateDto, Vacation.class);

        checkForVacationOverlap(vacation);
        editBookings(vacation);

        vacation = repository.save(vacation);

        return ResponseEntity.ok(mapper.map(vacation, VacationResponseDto.class));
    }

    public void editBookings(Vacation vacation) {
        List<Booking> bookings = bookingRepository.bookingDatesByEmployee(vacation.getEmployee().getId());

        for (Booking booking : bookings) {
            List<BookingDates> remove = new ArrayList<>();
            for (BookingDates date: booking.getDates()) {
                if (vacation.getStart().toLocalDate().equals(date.getDate())) {
                    remove.add(date);
                } else if (vacation.getEnd().toLocalDate().equals(date.getDate())) {
                    remove.add(date);
                } else if (vacation.getStart().toLocalDate().isBefore(date.getDate()) && vacation.getEnd().toLocalDate().isAfter(date.getDate())) {
                    remove.add(date);
                }
            }
            remove.forEach(element -> booking.getDates().remove(element));

            if (booking.getDates().isEmpty())
                bookingRepository.delete(booking);
            else
                bookingRepository.save(booking);
        }
    }


    public void checkForVacationOverlap(Vacation vacation) {
        List<Vacation> vacations = repository.findAllById(Collections.singleton(vacation.getEmployee().getId()));

        if (vacation.getStart().toLocalDate().isAfter(vacation.getEnd().toLocalDate()))
            throw new DateOutOfBoundException("Invalid dates for vacation");

        vacations.forEach(existVacation -> {
            if (existVacation.getStart().isBefore(vacation.getStart()) && existVacation.getEnd().isAfter(vacation.getStart()))
                throw new VacationOverlapException("Vacation overlapped with vacation id: " + existVacation.getId());
            if (existVacation.getStart().isBefore(vacation.getEnd()) && existVacation.getEnd().isAfter(vacation.getEnd()))
                throw new VacationOverlapException("Vacation overlapped with vacation id: " + existVacation.getId());
            if (
                    existVacation.getStart().equals(vacation.getStart())
                            || existVacation.getStart().equals(vacation.getEnd())
                            || existVacation.getEnd().equals(vacation.getStart())
                            || existVacation.getEnd().equals(vacation.getEnd())
            ) {
                throw new VacationOverlapException("Vacation overlapped with vacation id: " + existVacation.getId());
            }
        });
    }

    public ResponseEntity<List<VacationResponseDto>> getByEmployeeId(Long employeeId) {
        List<Vacation> allByEmployeeId = repository.findAllByEmployeeId(employeeId);
        return ResponseEntity.ok(allByEmployeeId.stream().map(vacation -> mapper.map(vacation, VacationResponseDto.class)).collect(Collectors.toList()));
    }
}
