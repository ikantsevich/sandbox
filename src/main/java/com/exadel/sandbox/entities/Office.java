package com.exadel.sandbox.entities;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "office")
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "of_id")
    private Long id;

    @Column(name = "pa_id")
    private Long parkingId;

    @Column(name = "ad_id")
    private Long addressId;

    @Column(name = "of_status")
    private String officeStatus;

    @CreatedDate
    @Column(name = "of_created")
    private LocalDateTime officeCreated;

    @LastModifiedDate
    @Column(name = "of_modified")
    private LocalDateTime officeModified;


}
