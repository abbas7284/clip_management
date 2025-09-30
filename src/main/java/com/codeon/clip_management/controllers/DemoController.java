package com.codeon.clip_management.controllers;

import com.codeon.clip_management.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class DemoController {
    private final UserService userService;

//    @GetMapping("/user")
//    public String userEndpoint() {
//        return "Hello USER!";
//    }

    @GetMapping("/user")
    public ResponseEntity<String> getUserInfo() {
        return userService.getUserInfo();
    }

    @GetMapping("/admin")
    public String adminEndpoint() {
        return "Hello ADMIN!";
    }


}
