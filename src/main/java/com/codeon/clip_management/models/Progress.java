package com.codeon.clip_management.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Progress")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Progress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "progress_id")
    private Long progressId;
    @Column(name = "target")
    private Long target;
    @Column(name = "total")
    private Long total;
    @Column(name = "label")
    private Long sumLabel;
    @Column(name = "month")
    private Long month;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;
}
