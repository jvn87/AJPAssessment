package com.controller;

import com.entity.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    // Create new user
    // curl -X POST http://localhost:9191/api/users -H "Content-Type: application/json" -d "{\"username\": \"user1\", \"password\": \"user123\", \"email\": \"user1@theshoe.com\", \"role\": \"USER\"}"
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
        // Check if the role exists and is not ADMIN
        if (user.getRole() != null && !user.getRole().equals(User.Role.ADMIN)) {
            User createdUser = userService.createUser(user);
            return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Empty/Admin roles are not allowed for new users", HttpStatus.FORBIDDEN);
        }
    }
    
    // Get all users
    // curl -X GET http://localhost:9191/api/users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    
    // Get user by username
    // curl -X GET http://localhost:9191/api/users/username/user1
    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // Get user by ID
    // curl -X GET http://localhost:9191/api/users/1
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // Delete user by username
    // curl -X DELETE http://localhost:9191/api/users/username/user1
    @DeleteMapping("/username/{username}")
    public ResponseEntity<Void> deleteUserByUsername(@PathVariable String username) {
        boolean isDeleted = userService.deleteUserByUsername(username);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Delete user by ID
    // curl -X DELETE http://localhost:9191/api/users/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        boolean isDeleted = userService.deleteUserById(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Login method
    // curl -X POST http://localhost:9191/api/users/login -H "Content-Type: application/json" -d "{\"username\": \"user1\", \"password\": \"user123\"}"
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User loginUser) {
        User user = userService.getUserByUsername(loginUser.getUsername());
        if (user != null && user.getPassword().equals(loginUser.getPassword())) {
            // Check user role and redirect accordingly
            if (user.getRole() == User.Role.ADMIN) {
                return ResponseEntity.ok("/admin/dashboard.html");
            } else if (user.getRole() == User.Role.USER) {
                return ResponseEntity.ok("/user/dashboard.html");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
}
