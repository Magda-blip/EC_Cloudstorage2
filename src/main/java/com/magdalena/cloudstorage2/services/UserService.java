package com.magdalena.cloudstorage2.services;


import com.magdalena.cloudstorage2.models.User;
import com.magdalena.cloudstorage2.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }
}
