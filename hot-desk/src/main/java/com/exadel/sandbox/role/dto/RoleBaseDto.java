package com.exadel.sandbox.role.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleBaseDto {
    @NotNull(message = "cannot be null")
    protected String name;
}