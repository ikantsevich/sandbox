package com.exadel.sandbox.dto;

import com.exadel.sandbox.entities.Employee;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VacationDto {
    int va_id;
    Employee employee;
    Date va_start;
    Date va_end;
    Date va_created;
    Date va_modified;
}
