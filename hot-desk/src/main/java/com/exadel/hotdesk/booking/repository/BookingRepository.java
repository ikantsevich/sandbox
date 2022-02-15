package com.exadel.hotdesk.booking.repository;

import com.exadel.hotdesk.booking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {


}
