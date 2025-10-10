package com.codeon.clip_management.repository;

import com.codeon.clip_management.models.Progress;
import com.codeon.clip_management.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProgressRepository extends JpaRepository<Progress, Long> {
    @Query(value = "select * from Progress where user_id=:userId and month=(select max(month) from Progress)", nativeQuery = true)
    List<Progress> findProgressByUserId(Long userId);

    @Query(value = "select * from Progress where user_id=:userId and month=(select max(month) from Progress)", nativeQuery = true)
    Progress findProgressLabelByUserId(Long userId);

    @Query(value = "select label from Progress where user_id=:userId and month=(select max(month) from Progress)", nativeQuery = true)
    Long findLabelByUserId(Long userId);

//    @Query(value = "update Progress set total = total - 50, label = label + 50 where progress_id =:", nativeQuery = true)
//    Progress findProgressById(Long progressId);
}
