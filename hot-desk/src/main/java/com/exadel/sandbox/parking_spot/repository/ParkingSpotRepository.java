package com.exadel.sandbox.parking_spot.repository;

import com.exadel.sandbox.parking_spot.entity.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {
    ParkingSpot findParkingSpotByOfficeId(Long id);

    @Query(value = "select d.date from Booking b join b.parkingSpot p join b.dates d where p.id =:id")
    List<LocalDate> findBookedDatesBySpotId(Long id);
}
