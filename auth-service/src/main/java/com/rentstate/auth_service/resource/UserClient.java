package com.rentstate.auth_service.resource;

import com.rentstate.auth_service.resource.api.resource.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service")
public interface UserClient {

    @GetMapping("/api/v1/users/{userId}")
    User getUserById(@PathVariable("userId") Long userId);

    @PostMapping("/api/v1/users")
    User registerUser(@RequestBody User user);

    @GetMapping("/api/v1/users/username/{username}")
    User getUserByUsername(@PathVariable("username") String username);
}