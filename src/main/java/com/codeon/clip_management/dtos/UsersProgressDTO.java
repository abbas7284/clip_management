package com.codeon.clip_management.dtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersProgressDTO {
    private String name;
    private String username;
    private Long target;
    private Long sumLabel;
    private Long month;
}
