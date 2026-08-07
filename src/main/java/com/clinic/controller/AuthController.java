package com.clinic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.clinic.dto.AuthResponse;
import com.clinic.dto.LoginRequest;
import com.clinic.dto.RegisterRequest;
import com.clinic.enums.Role;
import com.clinic.model.User;
import com.clinic.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse register(@RequestBody RegisterRequest request) {

        User user = new User();
        user.setUsername(request.username());
        user.setPassword(request.password());

        if (request.role() != null) {
            user.setRole(request.role());
        } else {
            user.setRole(Role.PATIENT);
        }

        User savedUser = authService.register(user);

        String token = authService.login(
                savedUser.getUsername(),
                request.password()
        );

        return new AuthResponse(
                token,
                savedUser.getRole().name(),
                savedUser.getUsername()
        );
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {

        String token = authService.login(
                request.username(),
                request.password()
        );

        return new AuthResponse(
                token,
                "LOGIN_SUCCESS",
                request.username()
        );
    }
}