package lk.ijse.springboot.microservice.userservice.controller;

import lk.ijse.springboot.microservice.userservice.dto.LoginRequest;
import lk.ijse.springboot.microservice.userservice.dto.RegisterRequest;
import lk.ijse.springboot.microservice.userservice.dto.UserResponse;
import lk.ijse.springboot.microservice.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {

        UserResponse user = userService.register(request);

        return ResponseEntity.ok(
                Map.of(
                        "message", "User registered successfully",
                        "data", user
                )
        );
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        UserResponse user = userService.login(request);

        return ResponseEntity.ok(
                Map.of(
                        "message", "Login successful",
                        "data", user
                )
        );
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long id,
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(userService.updateUser(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);

        return ResponseEntity.ok(
                Map.of("message", "User deleted successfully")
        );
    }
}