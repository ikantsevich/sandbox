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
public class Floor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fl_id")
    private Long id;

    @Column(name = "of_id")
    private Long ofId;

    @Column(name = "at_id")
    private Long atId;

    @Column(name = "fl_num")
    private int flNum;

    @Column(name = "kitchen_status")
    private String kitchenStatus;

    @Column(name = "meetingroom_status")
    private String meetingRoomStatus;

    @CreatedDate
    @Column(name = "fl_created")
    private LocalDateTime flCreated;

    @LastModifiedDate
    @Column(name = "fl_modified")
    private LocalDateTime flModified;

}
