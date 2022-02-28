package com.exadel.sandbox.security.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserRegisterDto {
    @NotNull
    @Size(min = 3,max = 50)
    private String firstName;

    @NotNull
    @Length(min=3,max=50)
    private String lastName;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;
}
