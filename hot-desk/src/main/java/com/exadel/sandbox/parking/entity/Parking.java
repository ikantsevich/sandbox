package com.exadel.sandbox.parking.entity;

import com.exadel.sandbox.address.entity.Address;
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
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pa_id")
    private Long id;

    @Column(name = "of_id")
    private Long office;

    @OneToOne
    @JoinColumn(name = "ad_id")
    private Address address;

    @CreatedDate
    @Column(name = "pa_created")
    private LocalDateTime created;

    @LastModifiedDate
    @Column(name = "pa_modified")
    private LocalDateTime modified;
}
