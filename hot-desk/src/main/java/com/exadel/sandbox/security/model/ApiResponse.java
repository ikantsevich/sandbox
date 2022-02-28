package com.exadel.sandbox.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor

@NoArgsConstructor
public class ApiResponse {
    private String message;
    private boolean success;
    private Object token;

    public ApiResponse(String message) {
        this.message = message;
        this.success = false;
    }

    public ApiResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

}

