package com.exadel.sandbox.officeFloor.entities;

import com.exadel.sandbox.address.entity.Address;
import com.exadel.sandbox.parking_spot.entity.ParkingSpot;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "office")
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "of_id")
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "office")
    @ToString.Exclude
    private List<ParkingSpot> parkingSpots;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ad_id")
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "office")
    private List<Floor> floors;

    @Column(name = "of_status")
    private String officeStatus;

    @CreatedDate
    @Column(name = "of_created")
    private LocalDateTime created;

    @LastModifiedDate
    @Column(name = "of_modified")
    private LocalDateTime modified;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Office office = (Office) o;
        return id != null && Objects.equals(id, office.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
