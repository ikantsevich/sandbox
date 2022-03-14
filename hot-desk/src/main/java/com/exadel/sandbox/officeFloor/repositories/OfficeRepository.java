package com.exadel.sandbox.officeFloor.repositories;

import com.exadel.sandbox.officeFloor.dto.officeDto.OfficeResponseDto;
import com.exadel.sandbox.officeFloor.entities.Office;
import com.exadel.sandbox.parking_spot.entity.ParkingSpot;
import com.exadel.sandbox.seat.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OfficeRepository extends JpaRepository<Office, Long> {
    @Query(value = "select s\n" +
            "from Seat s\n" +
            "where s.floor.office.id = :officeId\n" +
            "  and s.id not in (select s.id\n" +
            "                   from Seat s\n" +
            "                            left outer join s.bookings b\n" +
            "                            join b.dates d\n" +
            "                   where d.date in (:dates))")
    List<Seat> findFreeSeatsByOfficeIdAndDates(List<LocalDate> dates, Long officeId);

    @Query(value = "select p\n" +
            "from ParkingSpot p\n" +
            "where p.office.id = :officeId\n" +
            "  and p.id not in (select p.id from Booking b join b.parkingSpot p join b.dates d where d.date in (:dates))")
    List<ParkingSpot> findFreeSpotsByOfficeIdAndDates(Long officeId, List<LocalDate> dates);

    Office findOfficeByAddressId(Long id);
}