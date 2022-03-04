package com.exadel.sandbox.booking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class BookingDates {

    @Column(name = "date")
    private LocalDate date;
}
