package com.magdalena.cloudstorage2.services;


import com.magdalena.cloudstorage2.models.User;
import com.magdalena.cloudstorage2.repositories.UserRepository;
import com.magdalena.cloudstorage2.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * Creates a new user account.
     * The password is hashed using BCrypt before persisting.
     *
     * @param user user entity containing username and raw password
     * @return persisted user
     */
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /**
     * Retrieves the currently authenticated user based on the JWT token.
     *
     * @return authenticated user
     * @throws RuntimeException if the user cannot be found
     */
    public User getCurrentUser() {
        String username = SecurityUtils.getCurrentUsername();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }


}
