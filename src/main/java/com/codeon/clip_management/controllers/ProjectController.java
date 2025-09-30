package com.codeon.clip_management.controllers;

import com.codeon.clip_management.dtos.ProjectDTO;
import com.codeon.clip_management.models.Projects;
import com.codeon.clip_management.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vids")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping("/get")
    public List<ProjectDTO> getVids(){
        return projectService.getVids();
    }

    @PostMapping("/setEditing/{videoId}")
    public ResponseEntity<Projects> setEditing(
            @PathVariable Long videoId,
            @RequestBody Integer value){

        Projects projectsEditing = projectService.setEditing(videoId, value);
        return ResponseEntity.ok(projectsEditing);
    }
    @PostMapping("/setComplete/{videoId}")
    public ResponseEntity<Projects> setComplete(
            @PathVariable Long videoId,
            @RequestBody Integer value){

        Projects projectsComplete = projectService.setComplete(videoId, value);
        return ResponseEntity.ok(projectsComplete);
    }
}
