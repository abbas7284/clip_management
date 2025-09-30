package com.codeon.clip_management.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersProjectsDTO {
    private Long userId;
    private String name;
    private List<VideoDetail> videoNames;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class VideoDetail {
        private Long id;
        private String name;
        private Integer complete;
        private Integer editing;
    }
}
