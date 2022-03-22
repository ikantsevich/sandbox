package com.exadel.sandbox.booking.entity;

import com.exadel.sandbox.employee.entity.Employee;
import com.exadel.sandbox.parking_spot.entity.ParkingSpot;
import com.exadel.sandbox.seat.entity.Seat;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bo_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "em_id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spot_id")
    private ParkingSpot parkingSpot;

    @ElementCollection
    @CollectionTable(name = "booking_dates", joinColumns = @JoinColumn(name = "bo_id"))
    private List<BookingDates> dates;

    @CreatedDate
    @Column(name = "bo_created")
    private LocalDateTime created;

    @LastModifiedDate
    @Column(name = "bo_modified")
    private LocalDateTime modified;
}
