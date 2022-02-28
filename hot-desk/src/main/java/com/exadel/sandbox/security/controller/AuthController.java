package com.exadel.sandbox.security.controller;

import com.exadel.sandbox.security.model.ApiResponse;
import com.exadel.sandbox.security.model.LoginDto;
import com.exadel.sandbox.security.model.UserRegisterDto;
import com.exadel.sandbox.security.service.UserDetailServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserDetailServiceImp userService;

    @PostMapping("register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterDto userRegisterDto) {
        ApiResponse apiResponse = userService.registerUser(userRegisterDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        ApiResponse apiResponse = userService.login(loginDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


}
