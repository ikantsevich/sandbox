package com.exadel.sandbox.employee.entity;

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
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "em_id")
    private Long id;

    @OneToOne()
    @JoinColumn(name = "tg_info_id")
    private TgInfo tgInfo;

    @Column(name = "em_firstname", nullable = false)
    private String firstname;

    @Column(name = "em_lastname", nullable = false)
    private String lastname;

    @Column(name = "em_email", nullable = false)
    private String email;

    @Column(name = "em_position", nullable = false)
    private String position;

    @Column(name = "preferred_seat")
    private Integer preferredSeat;

    @Column(name = "em_start", nullable = false)
    private LocalDateTime employmentStart;

    @Column(name = "em_end")
    private LocalDateTime employmentEnd;

    @CreatedDate
    @Column(name = "em_created")
    private LocalDateTime created;

    @LastModifiedDate
    @Column(name = "em_modified")
    private LocalDateTime modified;
}
