package com.exadel.sandbox.permission.entity;

import com.exadel.sandbox.role.entity.Role;
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
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "per_id")
    private Long id;

    @Column(name = "per_name", nullable = false, unique = true)
    private String name;

    @CreatedDate
    @Column(name = "per_created")
    private LocalDateTime created;

    @LastModifiedDate
    @Column(name = "per_modified")
    private LocalDateTime modified;

    @ManyToMany(mappedBy = "permissions")
    private List<Role> roles = new ArrayList<>();
}