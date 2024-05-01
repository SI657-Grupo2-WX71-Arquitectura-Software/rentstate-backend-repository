package com.rentstate.user_service.service;

import com.rentstate.user_service.api.resource.userresource.RegisterRequest;
import com.rentstate.user_service.model.aggregates.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> getById(Long userId);
    List<User> getAll();
    Optional<User> update(User user);
    ResponseEntity<?> delete(Long userId);

    User register(RegisterRequest registerRequest);

    Optional<User> getUserByUsername(String username);


}