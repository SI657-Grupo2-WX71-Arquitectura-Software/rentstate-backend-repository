package com.rentstate.auth_service.resource.service;



import com.rentstate.auth_service.config.jwt.JwtService;
import com.rentstate.auth_service.resource.UserClient;
import com.rentstate.auth_service.resource.api.resource.AuthResponse;
import com.rentstate.auth_service.resource.api.resource.LoginRequest;
import com.rentstate.auth_service.resource.api.resource.RegisterRequest;
import com.rentstate.auth_service.resource.api.resource.User;
import com.rentstate.auth_service.resource.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;


@Service
@RequiredArgsConstructor
public class AuthService {



    private final UserClient userClient;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    private static final Logger LOGGER = Logger.getLogger(AuthService.class.getName());

    public AuthResponse login(LoginRequest loginRequest) {
        LOGGER.info("Attempting to authenticate user: " + loginRequest.getUsername());

        User user = userClient.getUserByUsername(loginRequest.getUsername());

        // Check if the user exists and the password matches
        if (user != null && user.getUsername() != null && !user.getUsername().isEmpty()
                && user.getPassword() != null && !user.getPassword().isEmpty()) {

            LOGGER.info("User exists, proceeding with password verification");

            if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                LOGGER.info("Password matches, proceeding with authentication");

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.getUsername(),
                                loginRequest.getPassword()
                        );

                // Authenticate the user
                authenticationManager.authenticate(authenticationToken);

                // Load the UserDetails using the UserDetailsService
                UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());

                String token = jwtService.getToken(userDetails);

                LOGGER.info("User successfully authenticated, returning AuthResponse");

                return AuthResponse.builder()
                        .token(token)
                        .userId(user.getId())
                        .build();
            } else {
                LOGGER.warning("Password does not match, throwing BadCredentialsException");
                throw new BadCredentialsException("Invalid username or password");
            }
        } else {
            // Handle the case when the user does not exist or the password does not match
            LOGGER.warning("User does not exist, throwing BadCredentialsException");
            throw new BadCredentialsException("Invalid username or password");
        }
    }
    public AuthResponse register(RegisterRequest registerRequest) {
        User user = new User();
        user.setName(registerRequest.getName());
        user.setLastName(registerRequest.getLastName());
        user.setUsername(registerRequest.getUsername());
        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
        user.setPassword(encodedPassword);

        User registeredUser = userClient.registerUser(user);

        // Use the username and password from the registerRequest, instead of the registeredUser
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                registerRequest.getUsername(),
                encodedPassword,
                new ArrayList<>()
        );

        String token = jwtService.getToken(userDetails);

        return AuthResponse.builder()
                .token(token)
                .userId(registeredUser.getId()) // Assuming the ID is returned correctly
                .build();
    }
}