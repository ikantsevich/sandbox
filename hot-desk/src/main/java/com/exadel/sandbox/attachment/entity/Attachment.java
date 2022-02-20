package com.exadel.sandbox.attachment.entity;

import com.exadel.sandbox.officeFloor.entities.Floor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "at_id")
    private Long id;

    @Column(name = "original_name")
    private String originalName;

    @Column(name = "name")
    private String name;

    @Column(name = "at_size")
    private Long size;

    @Column(name = "content_type")
    private String contentType;

    @CreatedDate
    @Column(name = "at_created")
    private LocalDateTime atCreated;

    @LastModifiedDate
    @Column(name = "at_modified")
    private LocalDateTime atModified;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "attachment")
    private Floor floor;


}
