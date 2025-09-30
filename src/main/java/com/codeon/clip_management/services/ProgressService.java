package com.codeon.clip_management.services;

import com.codeon.clip_management.dtos.ProgressDTO;
import com.codeon.clip_management.dtos.SetLabel;
import com.codeon.clip_management.mappers.ProgressMapper;
import com.codeon.clip_management.models.Progress;
import com.codeon.clip_management.models.Role;
import com.codeon.clip_management.models.Users;
import com.codeon.clip_management.repository.ProgressRepository;
import com.codeon.clip_management.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProgressService {
    private final UserRepository userRepository;
    private final ProgressRepository progressRepository;
    private final ProgressMapper progressMapper;

    public ResponseEntity<String> getUserName(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Users> byUsername = userRepository.findByUsername(username);
        String name = byUsername.get().getName();
        return ResponseEntity.ok(name);
    }

    @Transactional
    public List<ProgressDTO> getUserProgress(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        // Using orElseThrow for cleaner code when user is expected to exist
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + username));

        Long userId = user.getUserId();
        List<Progress> progressList = progressRepository.findProgressByUserId(userId);
        return progressMapper.toDTOList(progressList);
    }

    @Transactional
    public Progress setLabel(Long label){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Users> byUsername = userRepository.findByUsername(username);
        Long userId = byUsername.get().getUserId();

        Progress progress = progressRepository.findProgressLabelByUserId(userId);
        if (progress == null){
            throw new RuntimeException("Progress not found for user with id: " + userId);
        }
        if ((progress.getTotal() - label) >=0){
            progress.setTotal(progress.getTotal() - label);
        }else {
            progress.setTotal(progress.getTotal() - progress.getTotal());
        }


        progress.setSumLabel(progress.getSumLabel() + label);

        return progressRepository.save(progress);
    }

}

//@Transactional
//public Progress setLable(SetLabel request){
//
//    Progress progress = progressRepository.findById(request.getProgressId())
//            .orElseThrow(() -> new EntityNotFoundException("progress not found"));
//
//    Long newTotal = progress.getTotal() - request.getLabel();
//    if (newTotal<0){
//        throw new IllegalArgumentException("label more than total");
//    }
//    progress.setTotal(newTotal);
//    progress.setSumLabel(progress.getSumLabel() + request.getLabel());
//
//    return progressRepository.save(progress);
//}
