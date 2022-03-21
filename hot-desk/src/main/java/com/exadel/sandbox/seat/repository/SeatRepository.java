package com.exadel.sandbox.seat.repository;

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

    @Query(value = "select s from Seat s join s.floor f join f.office o where o.id = :id")
    List<Seat> findSeatsByOfficeId(Long id);

    @Query(value = "select s from Seat s join s.floor f join f.office o join o.address a where a.city = :city")
    List<Seat> findSeatsByCity(String city);

}
