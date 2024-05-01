package com.rentstate.user_service.service.implementation;



import com.rentstate.user_service.api.resource.exceptions.ResourceNotFoundException;
import com.rentstate.user_service.api.resource.userresource.RegisterRequest;
import com.rentstate.user_service.model.aggregates.User;
import com.rentstate.user_service.repositories.UserRepository;
import com.rentstate.user_service.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getById(Long userId) {
        return Optional.ofNullable(userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userId)));
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }


    @Override
    public Optional<User> update(User user) {
        return Optional.ofNullable(userRepository.findById(user.getId()).map(userToUpdate ->
                        userRepository.save(userToUpdate
                                .withName(user.getName())
                                .withLastName(user.getLastName())
                                .withAge(user.getAge())
                                .withGender(user.getGender())
                                .withDescription(user.getDescription())
                                .withIsPremium(user.getIsPremium())
                                .withPhotoUrl(user.getPhotoUrl())
                        ))
                .orElseThrow(() -> new ResourceNotFoundException("User", user.getId())));
    }

    @Override
    public ResponseEntity<?> delete(Long userId) {
        return userRepository.findById(userId).map(user -> {
                    userRepository.delete(user);
                    return ResponseEntity.ok().build();})
                .orElseThrow(() -> new ResourceNotFoundException("User", userId));
    }

    @Override
    public User register(RegisterRequest registerRequest) {
        if (userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already in use: " + registerRequest.getUsername());
        }
        User user = new User();
        user.setName(registerRequest.getName());
        user.setLastName(registerRequest.getLastName());
        user.setUsername(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword()); // The password is already encoded
        return userRepository.save(user);
    }
    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}