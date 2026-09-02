package com.nithish.employee_leave_management.controller;

import com.nithish.employee_leave_management.dto.LoginRequest;
import com.nithish.employee_leave_management.dto.LoginResponse;
import com.nithish.employee_leave_management.entity.User;
import com.nithish.employee_leave_management.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(
            UserService userService,
            PasswordEncoder passwordEncoder) {

        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest loginRequest) {

        User user = userService.getUserByUsername(
                loginRequest.getUsername()
        );

        if (!passwordEncoder.matches(
                loginRequest.getPassword(),
                user.getPassword())) {

            throw new IllegalArgumentException(
                    "Invalid username or password"
            );
        }

        LoginResponse response = new LoginResponse(
                "Login successful",
                user.getUsername(),
                user.getRole()
        );

        return ResponseEntity.ok(response);
    }
}