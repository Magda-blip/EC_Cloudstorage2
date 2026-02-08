package com.magdalena.cloudstorage2.controllers;

import com.magdalena.cloudstorage2.dto.AuthResponse;
import com.magdalena.cloudstorage2.dto.LoginRequest;
import com.magdalena.cloudstorage2.models.User;
import com.magdalena.cloudstorage2.security.JwtService;
import com.magdalena.cloudstorage2.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        User user = authService.authenticate(request);
        String token = jwtService.generateToken(user.getUsername());
        return new AuthResponse(token);

    }
}
