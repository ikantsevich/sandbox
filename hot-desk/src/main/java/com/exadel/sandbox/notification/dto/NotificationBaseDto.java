package com.exadel.sandbox.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotificationBaseDto {
    @Size(min = 4, max = 200, message = "should be 4 and 200 characters long")
    private String title;

    @Size(max = 384000, message = "should be 384000 at most")
    private String message;
}
