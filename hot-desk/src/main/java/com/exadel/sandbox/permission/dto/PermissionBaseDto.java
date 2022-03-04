package com.exadel.sandbox.permission.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionBaseDto {
    @NotNull(message = "cannot be null")
    protected String name;
}