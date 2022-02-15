package com.exadel.hotdesk.vacation.entities;

import com.exadel.hotdesk.employee.entity.Employee;
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
public class Vacation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "va_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "em_id")
    private Employee employee;

    @Column(name = "va_start", nullable = false)
    private LocalDateTime start;

    @Column(name = "va_end", nullable = false)
    private LocalDateTime end;

    @CreatedDate
    @Column(name = "va_created")
    private LocalDateTime created;

    @LastModifiedDate
    @Column(name = "va_modified")
    private LocalDateTime modified;
}
