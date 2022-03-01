package com.exadel.sandbox.booking.repository;

import com.exadel.sandbox.booking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findBookingsBySeatId(Long id);
    List<Booking> findBookingsByEmployeeId(Long id);
    List<Booking> findBookingsByParkingSpotId(Long id);
}
