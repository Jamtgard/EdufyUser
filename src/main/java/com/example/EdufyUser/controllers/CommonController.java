package com.example.EdufyUser.controllers;

import com.example.EdufyUser.models.DTO.UserDTO;
import com.example.EdufyUser.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//ED-87-SA
@RestController
@RequestMapping("/api/v1/user")
public class CommonController {

    private UserService userService;

    @Autowired
    public CommonController(UserService userService) {
        this.userService = userService;
    }

    //ED-87-SA
    @GetMapping("/getallusers")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
