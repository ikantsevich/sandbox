package com.exadel.sandbox.booking.repository;

import com.exadel.sandbox.booking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {


}
