package com.example.EdufyUser.controllers;

import com.example.EdufyUser.models.DTO.UserDTO;
import com.example.EdufyUser.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//ED-87-SA
@RestController
@RequestMapping("/user")
@PreAuthorize("hasAnyRole('user_admin', 'edufy_realm_admin', 'user_user')")
public class CommonController {

    private UserService userService;

    @Autowired
    public CommonController(UserService userService) {
        this.userService = userService;
    }

    //ED-87-SA
    @GetMapping("/all-users")
    public ResponseEntity<List<UserDTO>> getAllUsers(Authentication authentication) {
        return ResponseEntity.ok(userService.getAllUsers(authentication));
    }
}
