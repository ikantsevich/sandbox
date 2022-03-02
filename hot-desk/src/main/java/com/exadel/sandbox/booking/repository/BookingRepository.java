package com.exadel.sandbox.booking.repository;

import com.exadel.sandbox.booking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findBookingsBySeatId(Long id);
    List<Booking> findBookingsByEmployeeId(Long id);
    List<Booking> findBookingsByParkingSpotId(Long id);

//    Returns 1 if seat free on those dates else 0
    @Query(nativeQuery = true,
    value = "select count(distinct s.seat_id)\n" +
            "from booking b\n" +
            "         right join seat s on b.seat_id = s.seat_id\n" +
            "where s.seat_id =:seatId and s.seat_id not in (select b.seat_id\n" +
            "                                from booking b\n" +
            "                                         inner join booking_dates bd on b.bo_id = bd.bo_id\n" +
            "                                where bd.date in (:dates))")
    Integer checkIfSeatIsFree(Long seatId, List<Date> dates);

//    Returns 1 if parking spot is free those days else 0
    @Query(nativeQuery = true,
    value = "select count(distinct p.spot_id)\n" +
            "from booking b\n" +
            "         right join parking_spot p on b.spot_id = p.spot_id\n" +
            "where p.spot_id =:parkingId\n" +
            "  and p.spot_id not in (select b.spot_id\n" +
            "                        from booking b\n" +
            "                                 inner join booking_dates bd on b.bo_id = bd.bo_id\n" +
            "                        where bd.date in (:dates) and b.spot_id is not null)")
    Integer checkIfParkingSpotIsFree(Long parkingId, List<Date> dates);

//    returns 1 if Employee not booked on those days else 0
    @Query(nativeQuery = true,
    value = "select count(distinct e.em_id)\n" +
            "from booking b\n" +
            "         right join employee e on e.em_id = b.em_id\n" +
            "where e.em_id = :employeeId\n" +
            "  and e.em_id not in (select b.em_id\n" +
            "                      from booking b\n" +
            "                               inner join booking_dates bd on b.bo_id = bd.bo_id\n" +
            "                      where bd.date in (:dates))")
    Integer checkIfEmployeeNotBooked(Long employeeId, List<Date> dates);
}
