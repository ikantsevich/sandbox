package com.exadel.sandbox.booking.repository;

import com.exadel.sandbox.booking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findBookingsBySeatId(Long id);
    List<Booking> findBookingsByEmployeeId(Long id);
    List<Booking> findBookingsByParkingSpotId(Long id);

//    Returns 1 if seat free on those dates else 0
    @Query(nativeQuery = true,
    value = "select exists(\n" +
            "select *\n" +
            "     from booking b\n" +
            "                     right join seat s on s.seat_id = b.spot_id\n" +
            "            where s.seat_id = :seatId\n" +
            "              and s.seat_id not in (select b.seat_id\n" +
            "                                  from booking b\n" +
            "                                           inner join booking_dates bd on b.bo_id = bd.bo_id\n" +
            "                                  where bd.date in (:dates)))")
    Integer checkIfSeatIsFree(Long seatId, List<LocalDate> dates);

//    Returns 1 if parking spot is free those days else 0
    @Query(nativeQuery = true,
    value = "select exists(\n" +
            "select *\n" +
            "     from booking b\n" +
            "                     right join parking_spot p on p.spot_id = b.spot_id\n" +
            "            where p.spot_id = :parkingId\n" +
            "              and p.spot_id not in (select b.spot_id\n" +
            "                                  from booking b\n" +
            "                                           inner join booking_dates bd on b.bo_id = bd.bo_id\n" +
            "                                  where bd.date in (:dates)))")
    Integer checkIfParkingSpotIsFree(Long parkingId, List<LocalDate> dates);

//    returns 1 if Employee not booked on those days else 0
    @Query(nativeQuery = true,
    value = "select exists(\n" +
            "select *\n" +
            "     from booking b\n" +
            "                     right join employee e on e.em_id = b.em_id\n" +
            "            where e.em_id = :employeeId\n" +
            "              and e.em_id not in (select b.em_id\n" +
            "                                  from booking b\n" +
            "                                           inner join booking_dates bd on b.bo_id = bd.bo_id\n" +
            "                                  where bd.date in (:dates)))")
    Integer checkIfEmployeeNotBooked(Long employeeId, List<LocalDate> dates);

    @Query(value = "select b from Booking b join b.dates d where d.date >= :startDate and d.date <= :endDate")
    List<Booking> findBookingsInTimePeriod(LocalDate startDate, LocalDate endDate);

    @Query(value = "select b from Booking b join b.seat s join s.floor f join f.office o where o.id = :officeId")
    List<Booking> findBookingsByOfficeId(Long officeId);
}
