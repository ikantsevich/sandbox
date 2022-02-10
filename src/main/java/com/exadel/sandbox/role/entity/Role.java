package com.exadel.sandbox.role.entity;

import com.exadel.sandbox.permission.entity.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;


    @Column(name = "role_name", nullable = false, unique = true)
    private String name;


    @CreatedDate
    @Column(name = "role_created")
    private LocalDateTime created;


    @LastModifiedDate
    @Column(name = "role_modified")
    private LocalDateTime modified;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "role_per", joinColumns = {@JoinColumn(name = "role_id")}, inverseJoinColumns = {@JoinColumn(name = "per_id"),})
    List<Permission> permissions = new ArrayList<>();
}
