package com.codeon.clip_management.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Cost")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cost {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "cost_id")
        private Long costId;
        @Column(name = "cost")
        private Long cost;
}
