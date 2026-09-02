package com.nithish.employee_leave_management.security;

import com.nithish.employee_leave_management.entity.User;
import com.nithish.employee_leave_management.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final UserRepository userRepository;

    public SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public UserDetailsService userDetailsService() {

        return username -> {

            User user = userRepository.findByUsername(username)
                    .orElseThrow(() ->
                            new UsernameNotFoundException(
                                    "User not found: " + username
                            ));

            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRole())
                    .build();
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        // User APIs
                        .requestMatchers(
                                "/api/users/**",
                                "/api/auth/login"
                        ).permitAll()

                        // Admin-only APIs
                        .requestMatchers(
                                "/api/leaves/admin/**",
                                "/api/leaves/on-leave",
                                "/api/leaves/filter/**"
                        ).hasRole("ADMIN")

                        // Leave approval/rejection - Admin only
                        .requestMatchers(
                                "/api/leaves/*/approve",
                                "/api/leaves/*/reject"
                        ).hasRole("ADMIN")

                        // Other APIs require login
                        .anyRequest().authenticated()
                )

                .httpBasic(httpBasic -> {});

        return http.build();
    }
}