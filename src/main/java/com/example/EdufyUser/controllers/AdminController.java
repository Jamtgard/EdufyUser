package com.example.EdufyUser.controllers;

import com.example.EdufyUser.models.DTO.UserDTO;
import com.example.EdufyUser.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class AdminController {

    private final UserService userService;

    //ED-86-SA
    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    //ED-86-SA
    @GetMapping("/getuserbyid/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
}
