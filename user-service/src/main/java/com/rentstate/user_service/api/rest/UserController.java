package com.rentstate.user_service.api.rest;



import com.rentstate.user_service.api.resource.userresource.RegisterRequest;
import com.rentstate.user_service.api.resource.userresource.ResponseUserResource;
import com.rentstate.user_service.api.resource.userresource.UpdateUserResource;
import com.rentstate.user_service.model.aggregates.User;
import com.rentstate.user_service.service.RatingService;
import com.rentstate.user_service.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping(value = "/api/v1/users", produces = "application/json")
public class UserController {

    private final UserService userService;

    private final RatingService ratingService;

    public UserController(UserService userService, RatingService ratingService) {
        this.userService = userService;
        this.ratingService = ratingService;
    }

    @GetMapping
    public List<ResponseUserResource> getAllUsers() {
        List<User> users = userService.getAll();

        return users.stream()
                .map(ResponseUserResource::new)
                .collect(Collectors.toList());
    }

    @GetMapping("{userId}")
    public ResponseEntity<ResponseUserResource> getUserById(@PathVariable Long userId) {
        Optional<User> user = userService.getById(userId);

        ResponseUserResource userResponse = new ResponseUserResource(user.get());
        userResponse.setRankPoints(this.ratingService.getAverageRatingByRatedUser(user.get()));
        return ResponseEntity.ok(userResponse);
    }

    @PutMapping
    public ResponseEntity<ResponseUserResource> updateUser(@RequestBody UpdateUserResource updateUserResource) {

        Optional<User> userToUpdate = userService.getById(updateUserResource.getId());

        if(userToUpdate.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        userToUpdate.get().updateUser(updateUserResource);
        userService.update(userToUpdate.get());
        ResponseUserResource userResponse = new ResponseUserResource(userToUpdate.get());
        return ResponseEntity.ok(userResponse);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<ResponseUserResource> getUserByUsername(@PathVariable String username) {
        Optional<User> user = userService.getUserByUsername(username);

        if (user.isPresent()) {
            ResponseUserResource userResponse = new ResponseUserResource(user.get());
            return ResponseEntity.ok(userResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<ResponseUserResource> registerUser(@RequestBody RegisterRequest registerRequest) {
        User registeredUser = userService.register(registerRequest);
        ResponseUserResource userResponse = new ResponseUserResource(registeredUser);

        if (registerRequest.getName() == null || registerRequest.getName().isEmpty() ||
                registerRequest.getLastName() == null || registerRequest.getLastName().isEmpty() ||
                registerRequest.getUsername() == null || registerRequest.getUsername().isEmpty() ||
                registerRequest.getPassword() == null || registerRequest.getPassword().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

}