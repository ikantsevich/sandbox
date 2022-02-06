package com.exadel.sandbox.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleDto {
    private int id;
    private int name;
    private Date createdDate;
    private Date modifiedDate;
}
