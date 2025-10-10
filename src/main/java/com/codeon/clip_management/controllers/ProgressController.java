package com.codeon.clip_management.controllers;

import com.codeon.clip_management.dtos.ProgressDTO;
import com.codeon.clip_management.dtos.SetLabel;
import com.codeon.clip_management.mappers.ProgressMapper;
import com.codeon.clip_management.models.Progress;
import com.codeon.clip_management.services.ProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/panel")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class ProgressController {
    private final ProgressService progressService;
    private final ProgressMapper progressMapper;

    @GetMapping("/user")
    public List<ProgressDTO> getUserProgress(){
        return progressService.getUserProgress();
    }

    @GetMapping("/user/name")
    public ResponseEntity<String> getUserName() {
        return progressService.getUserName();
    }

    @GetMapping("/cost")
    public Float getCost(){
        return progressService.getCost();
    }

    @PostMapping("/set")
    public ResponseEntity<?> setLabel(@RequestBody SetLabel request){
        try {
            Progress updatedProgress = progressService.setLabel(request.getLabel());
            return ResponseEntity.ok(progressMapper.setToDTO(updatedProgress));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
