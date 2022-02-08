package com.exadel.sandbox.entities;

import liquibase.exception.DatabaseException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "attachment")
public class Attachment {
    @Id
    @Column(name = "at_id", nullable = false)
    private Long id;

    @Column(name = "chat_id")
    private String  chatId;

    @Column(name = "message_id")
    private Long messageId;

    @Column(name = "at_name")
    private String atName;

    @Column(name = "at_size")
    private Long atSize;

    @Column(name = "at_created")
    private Date atCreated;

    @Column(name = "at_modified")
    private Date atModified;


}
