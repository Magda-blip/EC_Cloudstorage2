package com.magdalena.cloudstorage2.controllers;

import com.magdalena.cloudstorage2.dto.LoginRequest;
import com.magdalena.cloudstorage2.models.User;
import com.magdalena.cloudstorage2.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        User user = authService.authenticate(request);
        return "LOGIN OK for user: " + user.getUsername();
    }
}
