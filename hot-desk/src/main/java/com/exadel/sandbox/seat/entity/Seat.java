package com.exadel.sandbox.seat.entity;

import com.exadel.sandbox.booking.entity.Booking;
import com.exadel.sandbox.employee.entity.Employee;
import com.exadel.sandbox.equipment.entity.Equipment;
import com.exadel.sandbox.officeFloor.entities.Floor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "fl_id")
    private Floor floor;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "seat")
    private List<Booking> bookings;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "seat")
    private List<Equipment> equipments;

    @Column(name = "seat_num", nullable = false)
    private int number;

    @Column(name = "seat_status", nullable = false)
    private String status;

    @Column(name = "seat_desc", nullable = false)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "preferredSeat")
    private List<Employee> employees;

    @CreatedDate
    @Column(name = "seat_created")
    private LocalDateTime created;

    @LastModifiedDate
    @Column(name = "seat_modified")
    private LocalDateTime modified;
}
