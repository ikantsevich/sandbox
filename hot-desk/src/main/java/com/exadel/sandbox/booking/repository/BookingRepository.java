package com.exadel.sandbox.booking.repository;

import com.exadel.sandbox.booking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByStartDateBetween(LocalDate startDate, LocalDate endDate);

}
