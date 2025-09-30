package com.codeon.clip_management.controllers;

import com.codeon.clip_management.models.Users;
import com.codeon.clip_management.repository.UserRepository;
import com.codeon.clip_management.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {
    private final UserRepository userRepo;
    private final JwtService jwtService;
    private final PasswordEncoder encoder;

    public AuthController(UserRepository userRepo, JwtService jwtService, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.jwtService = jwtService;
        this.encoder = encoder;
    }

    @PostMapping("/login")
    public String login(@RequestBody Users users) {
        var dbUser = userRepo.findByUsername(users.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (encoder.matches(users.getPassword(), dbUser.getPassword())) {
            // استفاده از متد جدید که نقش کاربر را هم در نظر می‌گیرد
            return jwtService.generateToken(dbUser.getUsername(), dbUser.getRole());
        }
        throw new RuntimeException("Invalid credentials");
    }
}