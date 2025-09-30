package com.codeon.clip_management.repository;

import com.codeon.clip_management.models.Progress;
import com.codeon.clip_management.models.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Projects, Long> {
    @Query(value = "select * from Project where user_id=:userId", nativeQuery = true)
    List<Projects> findProjectByUserId(Long userId);

//    @Query(value = "select editing,complete from Project where video_id=:videoId", nativeQuery = true)
//    Projects findProjectById(Long videoId);
}
