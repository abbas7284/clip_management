package com.codeon.clip_management.services;

import com.codeon.clip_management.models.Role;
import com.codeon.clip_management.models.Users;
import com.codeon.clip_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public ResponseEntity<String> getUserInfo(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Users> byUsername = userRepository.findByUsername(username);
        Long userId = byUsername.get().getUserId();
        String name = byUsername.get().getName();
        Role role = byUsername.get().getRole();
        return ResponseEntity.ok(
                "کاربر جاری: " + userId
                        + " \nنام: " + name
                        + " \nسمت: " + role

        );
    }
}
