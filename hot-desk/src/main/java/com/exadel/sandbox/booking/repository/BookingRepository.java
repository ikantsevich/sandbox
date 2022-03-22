package com.exadel.sandbox.booking.repository;

import com.exadel.sandbox.booking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Modifying
    //    Returns the dates which seat is not free
    @Query(value = "select d.date from Booking b join b.seat s join b.dates d where s.id = :seatId and d.date in (:dates)")
    List<LocalDate> checkSeatBookingDates(Long seatId, List<LocalDate> dates);

    //    Returns the dates which parking is not free
    @Query(value = "select d.date from Booking b join b.parkingSpot p join b.dates d where p.id = :spotId and d.date in (:dates)")
    List<LocalDate> checkParkingSpotBookedDates(Long spotId, List<LocalDate> dates);

    //    Returns the dates which employee already made a booking
    @Query(value = "select d.date from Booking b join b.employee e join b.dates d where e.id = :id and d.date in (:dates)")
    List<LocalDate> checkEmployeeBookedDates(Long id, List<LocalDate> dates);

    @Query(value = "select distinct b from Booking b join b.dates d where d.date >= :startDate and d.date <= :endDate")
    List<Booking> findBookingsInTimePeriod(LocalDate startDate, LocalDate endDate);

    @Query(value = "select b from Booking b join b.seat s join s.floor f join f.office o where o.id = :officeId")
    List<Booking> findBookingsByOfficeId(Long officeId);

    @Query(value = "select b from Booking b join b.seat s join s.floor f join f.office o join o.address a where a.city = :cityName")
    List<Booking> findBookingsByCity(String cityName);

    @Query(value = "select b from Booking b join b.seat s join s.floor f where f.id = :floorId")
    List<Booking> findBookingsByFloorId(Long floorId);

    @Query(value = "select b from Booking b where b.employee.id = :employeeId")
    List<Booking> findBookingsByEmployeeId(Long employeeId);

}
