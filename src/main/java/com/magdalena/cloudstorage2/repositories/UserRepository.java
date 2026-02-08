package com.magdalena.cloudstorage2.repositories;

import com.magdalena.cloudstorage2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}

