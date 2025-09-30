package com.codeon.clip_management.services;

import com.codeon.clip_management.dtos.*;
import com.codeon.clip_management.mappers.UsersMapper;
import com.codeon.clip_management.mappers.UsersProgressMapper;
import com.codeon.clip_management.mappers.UsersProgressMapper1;
import com.codeon.clip_management.mappers.UsersProjectsMapper;
import com.codeon.clip_management.models.Progress;
import com.codeon.clip_management.models.Projects;
import com.codeon.clip_management.models.Users;
import com.codeon.clip_management.repository.ProgressRepository;
import com.codeon.clip_management.repository.ProjectRepository;
import com.codeon.clip_management.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final ProgressRepository progressRepository;
    private final UsersProgressMapper usersProgressMapper;
    private final UsersMapper usersMapper;
    private final PasswordEncoder encoder;
    private final UsersProjectsMapper usersProjectsMapper;
    private final ProjectRepository projectRepository;
    private final UsersProgressMapper1 usersProgressMapper1;

    @Transactional
    public List<UsersProgressDTO> getAll(){
        List<Users> users = userRepository.findAll();

        // 2. Use a stream to process each user individually.
        return users.stream()
                .map(user ->
                        // For each user, find their latest progress record from their own list of progresses.
                        user.getProgress().stream()
                                .max(Comparator.comparing(Progress::getProgressId)) // Assumes higher ID is newer
                                // 3. If a record is found, use the mapper to create the DTO.
                                // IMPORTANT: Your mapper needs a method like: toDTO(Users user, Progress progress)
                                .map(latestProgress -> usersProgressMapper.toDto(user, latestProgress))
                                .orElse(null) // If the user has no progress, result is null.
                )
                .filter(Objects::nonNull) // 4. Filter out users who had no progress records.
                .collect(Collectors.toList()); // 5. Collect the final, clean list of DTOs.
    }

    @Transactional
    public List<UsersDTO> getAllUsers(){
        List<Users> users = userRepository.findAll();
        return usersMapper.toDTOList(users);

        }

    @Transactional
    public void delUser(Long userId){
        userRepository.deleteById(userId);

    }

    @Transactional
    public void delVid(Long videoId){
        projectRepository.deleteById(videoId);

    }

    @Transactional
    public void delProgress(Long progressId){
        progressRepository.deleteById(progressId);

    }

    public void userUpdate(Long userId, UsersDTO usersDTO){
        Users existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));
        existingUser.setUsername(usersDTO.getUsername());
        existingUser.setName(usersDTO.getName());
        existingUser.setRole(usersDTO.getRole());
        if (usersDTO.getPassword() != null && !usersDTO.getPassword().isEmpty()){
            existingUser.setPassword(encoder.encode(usersDTO.getPassword()));
        }

        userRepository.save(existingUser);
    }

    public String register(Users users){
        users.setPassword(encoder.encode(users.getPassword()));
        userRepository.save(users);
        return "User registered";
    }

    @Transactional
    public List<UsersProjectsDTO> getVideos(){
        List<Users> users = userRepository.findAll();
        return usersProjectsMapper.toDtoList(users);
    }

    public List<UsersProgressDTO1> getProgress(){
        List<Users> users = userRepository.findAll();
        return usersProgressMapper1.toDtoList(users);
    }

    @Transactional
    public void addProgress(Long userId, SetProgressDTO setProgressDTO){
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Progress newProgress = usersProgressMapper1.toEntity(setProgressDTO, user);
        newProgress.setTotal(newProgress.getTarget());
        progressRepository.save(newProgress);


    }

    @Transactional
    public void addVids(Long userId, String video){
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Projects newProject = usersProjectsMapper.toEntity(video, user);

        projectRepository.save(newProject);


    }
}
