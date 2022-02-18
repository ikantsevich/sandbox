package com.exadel.sandbox.notification.entity;

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
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notif_id")
    private Long id;


    //TODO make attachment many to many attachment relationship
//    @ManyToMany
//    @JoinColumn(name = "att_id")
    @Column(name = "att_id")
    private Long atttachmentId;

    @Column(name = "message", nullable = false)
    private String message;

    @CreatedDate
    @Column(name = "notif_created")
    private LocalDateTime created;

    @LastModifiedDate
    @Column(name = "notif_modified")
    private LocalDateTime modified;

}
