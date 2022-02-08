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
@Table(name = "office")
public class Office {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "pa_id")
    private Long parkingId;

    @Column(name = "ad_id")
    private Long addressId;

    @Column(name = "of_status")
    private String  officeStatus;

    @Column(name = "of_created")
    private Date officeCreated;

    @Column(name = "of_modified")
    private Date officeModified;


}
