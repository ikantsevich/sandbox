package com.exadel.sandbox.entities;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Vacation {
    @Id
    Integer va_id;

    @OneToOne
    @JoinColumn(name = "em_id")
    Employee employee;
    Date va_start;
    Date va_end;
    Date va_created;
    Date va_modified;
}
