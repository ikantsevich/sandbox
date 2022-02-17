package com.exadel.sandbox.em_notif.entity;

import com.exadel.sandbox.employee.entity.Employee;
import com.exadel.sandbox.notification.entity.Notification;
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
public class EmployeeNotification {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "em_notif_id")
    private Long id;


    @OneToOne
    @JoinColumn(name = "em_id")
    private Employee employeeId;

    @OneToOne
    @JoinColumn(name = "notif_id")
    private Notification notification;

    @CreatedDate
    @Column(name = "em_notif_created")
    private LocalDateTime created;


    @LastModifiedDate
    @Column(name = "em_notif_modified")
    private LocalDateTime modified;

}
