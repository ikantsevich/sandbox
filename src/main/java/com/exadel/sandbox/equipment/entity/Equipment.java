package com.exadel.sandbox.equipment.entity;

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
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eq_id")
    private Long id;

    @Column(name = "seat_id", nullable = false)
    private Long seatId;

    @Column(name = "eq_name", nullable = false)
    private String name;

    @Column(name = "eq_type")
    private String type;

    @CreatedDate
    @Column(name = "eq_created")
    private LocalDateTime created;

    @LastModifiedDate
    @Column(name = "eq_modified")
    private LocalDateTime modified;
}
