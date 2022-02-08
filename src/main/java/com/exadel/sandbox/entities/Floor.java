package com.exadel.sandbox.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "floor")
public class Floor {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
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

    @Column(name = "fl_created")
    private Date flCreated;

    @Column(name = "fl_modified")
    private Date flModified;

}
