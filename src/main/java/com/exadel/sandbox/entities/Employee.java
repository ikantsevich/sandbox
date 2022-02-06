package com.exadel.sandbox.entities;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer emId;

    @OneToOne
    @JoinColumn(name = "tg_info_id")
    TgInfo tgInfo;

    String emFirstname;
    String emLastname;
    String emEmail;
    String emPosition;
    Integer preferredSeat;
    Date emStart;
    Date emEnd;
    Date emCreated;
    Date emModified;
}
