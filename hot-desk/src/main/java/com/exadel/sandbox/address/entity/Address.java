package com.exadel.sandbox.address.entity;

import com.exadel.sandbox.officeFloor.entities.Office;
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
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ad_id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "address")
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
}
