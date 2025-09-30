package com.codeon.clip_management.services;

import com.codeon.clip_management.dtos.ProgressDTO;
import com.codeon.clip_management.dtos.ProjectDTO;
import com.codeon.clip_management.mappers.ProgressMapper;
import com.codeon.clip_management.mappers.ProjectMapper;
import com.codeon.clip_management.models.Progress;
import com.codeon.clip_management.models.Projects;
import com.codeon.clip_management.models.Users;
import com.codeon.clip_management.repository.ProgressRepository;
import com.codeon.clip_management.repository.ProjectRepository;
import com.codeon.clip_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public List<ProjectDTO> getVids(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Users> byUsername = userRepository.findByUsername(username);
        Long userId = byUsername.get().getUserId();
        List<Projects> projectsList = projectRepository.findProjectByUserId(userId);
        return projectMapper.toDTOList(projectsList);
    }

    public Projects setEditing(Long videoId, Integer editing){
        Projects projects = projectRepository.findById(videoId)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + videoId));
        projects.setEditing(editing);
        if (editing == 1){
            projects.setComplete(0);
        }
        return projectRepository.save(projects);
    }

    public Projects setComplete(Long videoId, Integer complete){
        Projects projects = projectRepository.findById(videoId)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + videoId));
        projects.setComplete(complete);
        if (complete == 1){
            projects.setEditing(0);
        }
        return projectRepository.save(projects);
    }
}
