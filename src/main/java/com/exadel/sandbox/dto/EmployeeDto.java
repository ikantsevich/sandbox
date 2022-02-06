package com.exadel.sandbox.dto;

import com.exadel.sandbox.entities.TgInfo;
import liquibase.pro.packaged.A;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeDto {
    Integer emId;
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
