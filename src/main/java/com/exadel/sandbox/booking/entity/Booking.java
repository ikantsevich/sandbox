package com.exadel.sandbox.booking.entity;

import com.exadel.sandbox.address.entity.Address;
import com.exadel.sandbox.employee.entity.Employee;
import com.exadel.sandbox.parking_spot.entity.ParkingSpot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bo_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "em_id")
    private Employee employee;

    @Column(name = "seat_id")
    private Long seat;

    @OneToOne
    @JoinColumn(name = "spot_id")
    private ParkingSpot parkingSpot;

    @Column(name = "bo_status", nullable = false)
    private String status;

    @Column(name = "bo_start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "bo_end_date")
    private LocalDateTime endDate;

    @CreatedDate
    @Column(name = "bo_created")
    private LocalDateTime created;

    @LastModifiedDate
    @Column(name = "bo_modified")
    private LocalDateTime modified;
}
