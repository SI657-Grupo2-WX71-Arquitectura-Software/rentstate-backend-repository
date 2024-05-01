package com.rentstate.auth_service.resource.repository;

import com.rentstate.auth_service.resource.api.resource.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}