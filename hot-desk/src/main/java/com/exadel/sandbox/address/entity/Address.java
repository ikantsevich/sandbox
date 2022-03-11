package com.exadel.sandbox.address.entity;

import com.exadel.sandbox.officeFloor.entities.Office;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ad_id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "address", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Office office;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "building_num", nullable = false)
    private int buildingNum;

    @Column(name = "zip_code")
    private String zipCode;

    @CreatedDate
    @Column(name = "ad_created")
    private LocalDateTime created;

    @LastModifiedDate
    @Column(name = "ad_modified")
    private LocalDateTime modified;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Address address = (Address) o;
        return id != null && Objects.equals(id, address.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
