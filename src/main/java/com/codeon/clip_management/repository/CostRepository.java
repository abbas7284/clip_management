package com.codeon.clip_management.repository;

import com.codeon.clip_management.models.Cost;
import com.codeon.clip_management.models.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CostRepository extends JpaRepository<Cost, Long> {
    @Query(value = "select cost from cost where cost_id=1", nativeQuery = true)
    Long findCost();
}
