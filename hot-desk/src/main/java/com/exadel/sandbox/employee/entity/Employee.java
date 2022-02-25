package com.exadel.sandbox.employee.entity;

import com.exadel.sandbox.role.entity.Role;
import com.exadel.sandbox.seat.entity.Seat;
import com.exadel.sandbox.vacation.entities.Vacation;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "em_id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "preferred_seat")
    private Seat preferredSeat;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "employee_role", joinColumns = {@JoinColumn(name = "em_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id"),})
    private List<Role> roles = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employee")
    private Set<Vacation> vacations;


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
