package com.codeon.clip_management.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersProgressDTO1 {
    private Long userId;
    private String name;
    private List<progress> progress;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class progress{
        private Long progressId;
        private Long target;
        private Long total;
        private Long sumLabel;
        private Long month;
    }





    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UsersProgressDTO2 {
        private Long userId;
        private String name;
        private List<progress1> progress1;

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class progress1{
            private Long progressId;
            private Long target;
            private Long sumLabel;
        }
    }
}
