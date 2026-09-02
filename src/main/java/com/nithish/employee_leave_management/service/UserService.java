package com.nithish.employee_leave_management.service;

import com.nithish.employee_leave_management.entity.User;
import com.nithish.employee_leave_management.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Find user by username
    public User getUserByUsername(String username) {

        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found with username: " + username
                        ));
    }

    // Create a new user
    public User createUser(User user) {

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalArgumentException(
                    "Username already exists: " + user.getUsername()
            );
        }

        return userRepository.save(user);
    }
}