package com.nithish.employee_leave_management.controller;

import com.nithish.employee_leave_management.entity.User;
import com.nithish.employee_leave_management.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Create a new user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {

        return ResponseEntity.ok(
                userService.createUser(user)
        );
    }

    // Get user by username
    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(
            @PathVariable String username) {

        return ResponseEntity.ok(
                userService.getUserByUsername(username)
        );
    }
}