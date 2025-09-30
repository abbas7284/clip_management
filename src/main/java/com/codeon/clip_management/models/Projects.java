package com.codeon.clip_management.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Project")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_id")
    private Long videoId;
    @Column(name = "video")
    private String video;
    @Column(name = "complete")
    private Integer complete;
    @Column(name = "editing")
    private Integer editing;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;
}
