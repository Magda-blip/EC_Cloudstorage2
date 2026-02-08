package com.magdalena.cloudstorage2.controllers;


import com.magdalena.cloudstorage2.models.User;
import com.magdalena.cloudstorage2.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

}
