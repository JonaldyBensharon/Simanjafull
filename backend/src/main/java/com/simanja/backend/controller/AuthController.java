package com.simanja.backend.controller;

import com.simanja.backend.dto.ApiResponse;
import com.simanja.backend.dto.LoginRequest;
import com.simanja.backend.dto.RegisterRequest;
import com.simanja.backend.dto.UserResponse;
import com.simanja.backend.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    public AuthController(
            AuthService authService
    ) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(
            @RequestBody LoginRequest request
    ) {

        UserResponse user =
                authService.login(request);

        return ResponseEntity.ok(
                new ApiResponse(
                        true,
                        "Login berhasil",
                        user
                )
        );
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(
            @RequestBody RegisterRequest request
    ) {

        UserResponse user =
                authService.register(request);

        return ResponseEntity.ok(
                new ApiResponse(
                        true,
                        "Registrasi berhasil",
                        user
                )
        );
    }
}