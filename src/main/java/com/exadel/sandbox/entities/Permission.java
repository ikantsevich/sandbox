package com.exadel.sandbox.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Permission {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer per_id;


    @Column(nullable = false, unique = true)
    private int name;


    @CreatedDate
    private Date createdDate;


    @LastModifiedDate
    private Date modifiedDate;

    @ManyToMany(mappedBy = "permissions")
    private List<Role> roles = new ArrayList<>();
}
