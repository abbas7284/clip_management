package com.codeon.clip_management.controllers;

import com.codeon.clip_management.dtos.*;
import com.codeon.clip_management.models.Users;
import com.codeon.clip_management.repository.UserRepository;
import com.codeon.clip_management.security.JwtService;
import com.codeon.clip_management.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/panel/admin")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class AdminController {

    private final UserRepository userRepository;
    private final AdminService adminService;

    @PostMapping("/register")
    public String register(@RequestBody Users users) {
        return adminService.register(users);
    }

    @GetMapping("/users/progress")
    public List<UsersProgressDTO> getAllProgress(){
        return adminService.getAll();
    }

    @GetMapping("/users")
    public List<UsersDTO> getAllUsers(){
        return adminService.getAllUsers();
    }

    @DeleteMapping("/del/{userId}")
    public void delUser(@PathVariable Long userId){
        adminService.delUser(userId);
    }

    @DeleteMapping("/video/del/{videoId}")
    public void delVid(@PathVariable Long videoId){
        adminService.delVid(videoId);
    }

    @DeleteMapping("/progress/del/{progressId}")
    public void delProgress(@PathVariable Long progressId){
        adminService.delProgress(progressId);
    }

    @PostMapping("/users/update/{userId}")
    public void userUpdate(@PathVariable Long userId, @RequestBody UsersDTO usersDTO){
        adminService.userUpdate(userId, usersDTO);
    }

    @GetMapping("/videos")
    public List<UsersProjectsDTO> getVideo(){
        return adminService.getVideos();
    }

    @GetMapping("/progress")
    public List<UsersProgressDTO1> getProgress(){
        return adminService.getProgress();
    }

    @PostMapping("/set/video/{userId}")
    public ResponseEntity<Void> addVids(
            @PathVariable Long userId,
            @RequestBody SetVidDTO setVidDTO){

        adminService.addVids(userId, setVidDTO.getVideo());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/set/progress/{userId}")
    public ResponseEntity<Void> addProgress(
            @PathVariable Long userId,
            @RequestBody SetProgressDTO setProgressDTO){

        adminService.addProgress(userId, setProgressDTO);
        return ResponseEntity.ok().build();
    }

}
