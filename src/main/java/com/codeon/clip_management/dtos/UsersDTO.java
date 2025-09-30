package com.codeon.clip_management.dtos;

import com.codeon.clip_management.models.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UsersDTO {
    private Long userId;
    private String name;
    private String username;
    private String password;
    private Role role;
}
