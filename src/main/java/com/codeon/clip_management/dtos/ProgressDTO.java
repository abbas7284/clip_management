package com.codeon.clip_management.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ProgressDTO {
    private Long progressId;
    private Long target;
    private Long total;
    private Long sumLabel;
    private Long month;
}
