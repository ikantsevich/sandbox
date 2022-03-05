package com.exadel.sandbox.officeFloor.repositories;

import com.exadel.sandbox.officeFloor.dto.officeDto.OfficeResponseDto;
import com.exadel.sandbox.officeFloor.entities.Office;
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

    Office findOfficeByAddressId(Long id);
}