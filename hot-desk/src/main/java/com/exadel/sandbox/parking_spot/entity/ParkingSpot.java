package com.exadel.sandbox.parking_spot.entity;

import com.exadel.sandbox.booking.entity.Booking;
import com.exadel.sandbox.officeFloor.entities.Office;
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
public class ParkingSpot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spot_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "of_id")
    private Office office;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "seat")
    private List<Booking> bookings;

    @Column(name = "spot_status")
    private String status;

    @Column(name = "spot_num")
    private Integer spotNum;

    @Column(name = "e_charger")
    private Boolean hasECharger;

    @CreatedDate
    @Column(name = "spot_created")
    private LocalDateTime created;

    @LastModifiedDate
    @Column(name = "spot_modified")
    private LocalDateTime modified;
}
