package com.exadel.sandbox.entities;

import liquibase.exception.DatabaseException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

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

    @Column(name = "chat_id")
    private String chatId;

    @Column(name = "massage_id")
    private Long messageId;

    @Column(name = "at_name")
    private String atName;

    @Column(name = "at_size")
    private Long atSize;

    @CreatedDate
    @Column(name = "at_created")
    private LocalDateTime atCreated;

    @LastModifiedDate
    @Column(name = "at_modified")
    private LocalDateTime atModified;


}
