package com.exadel.sandbox.seat.entity;

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
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Long id;

    @JoinColumn(name = "fl_id")
    private Long floorId;

    @Column(name = "seat_num", nullable = false)
    private int number;

    @Column(name = "seat_status", nullable = false)
    private String status;

    @Column(name = "seat_desc", nullable = false)
    private String description;

    @CreatedDate
    @Column(name = "seat_created")
    private LocalDateTime created;

    @LastModifiedDate
    @Column(name = "seat_modified")
    private LocalDateTime modified;
}
