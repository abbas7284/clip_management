package com.codeon.clip_management.services;

import com.codeon.clip_management.dtos.ProgressDTO;
import com.codeon.clip_management.dtos.SetLabel;
import com.codeon.clip_management.dtos.UsersProgressDTO1;
import com.codeon.clip_management.mappers.ProgressMapper;
import com.codeon.clip_management.mappers.UsersProgressMapper1;
import com.codeon.clip_management.models.Progress;
import com.codeon.clip_management.models.Role;
import com.codeon.clip_management.models.Users;
import com.codeon.clip_management.repository.CostRepository;
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

import static com.codeon.clip_management.models.Role.ROLE_USER;

@Service
@RequiredArgsConstructor
public class ProgressService {
    private final UserRepository userRepository;
    private final ProgressRepository progressRepository;
    private final ProgressMapper progressMapper;
    private final CostRepository costRepository;
    private final AdminService adminService;
    private final UsersProgressMapper1 usersProgressMapper1;

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
    public List<UsersProgressDTO1.UsersProgressDTO2> percent(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        // Using orElseThrow for cleaner code when user is expected to exist
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + username));

        Long userId = user.getUserId();
        List<Users> users = userRepository.findAll();

        if (userId == 7){
            return null;
        }else {
            return usersProgressMapper1.toDtoList2(users);
        }

//        Long percent = label/target*100;
//        return percent;
    }

    @Transactional
    public Float getCost(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        // Using orElseThrow for cleaner code when user is expected to exist
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + username));

        Long userId = user.getUserId();
        Role userRole = user.getRole();
        List<Progress> progressList = progressRepository.findProgressByUserId(userId);
        Long cost = costRepository.findCost();
        Long label = progressRepository.findLabelByUserId(userId);
        Long label1 = progressRepository.findLabelByUserId(4L);
        Long label2 = progressRepository.findLabelByUserId(5L);
        Long label3 = progressRepository.findLabelByUserId(8L);
        Long labeln = label1+label2+label3;
        if (userId == 3 || userId == 7){
            return (float) ((cost*label*10)/10000);
        } else if (userId == 10) {
            return (float) (((cost*label*10)/10000)+((cost*labeln*6)/10000));
        } else {
            return (float) ((cost*label*4)/10000);
        }
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
