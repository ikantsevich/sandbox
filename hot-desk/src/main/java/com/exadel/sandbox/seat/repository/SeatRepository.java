package com.exadel.sandbox.seat.repository;

import com.exadel.sandbox.reports.dto.SeatReportDto;
import com.exadel.sandbox.seat.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findSeatsByFloorId(Long id);

    @Query(value = "select d.date from Seat s join s.bookings b join b.dates d where s.id = :id and d.date >= :startDate and d.date <= :endDate")
    List<LocalDate> findSeatsBookedDates(Long id, LocalDate startDate, LocalDate endDate);

    @Query(value = "select new com.exadel.sandbox.reports.dto.SeatReportDto(s.id, s.number, count(d.date)) from Seat s left outer join s.bookings b left outer join b.dates d join s.floor.office o where (d.date = null or  d.date between :startDate and :endDate) and o.id = :officeId group by s.id")
    List<SeatReportDto> findReportOfSeatsById(Long officeId, LocalDate startDate, LocalDate endDate);
}
