package com.exadel.sandbox.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role {
    @Id
    @Column(name = "role_id", nullable = false)
    private int id;
    @Column(name = "role_name", nullable = false, unique = true)
    private int name;
    @Column(name = "role_created")
    private Date CreatedDate;
    @Column(name = "role_modified")
    private Date modifiedDate;
}
