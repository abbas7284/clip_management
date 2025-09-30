package com.codeon.clip_management.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetProgressDTO {
    private Long target;
    private Long total;
    private Long month;
}
