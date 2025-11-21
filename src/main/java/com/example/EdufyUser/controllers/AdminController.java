package com.example.EdufyUser.controllers;

import com.example.EdufyUser.models.DTO.CreateUserDTO;
import com.example.EdufyUser.models.DTO.UserDTO;
import com.example.EdufyUser.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@PreAuthorize("hasAnyRole('user_admin', 'edufy_realm_admin')")
public class AdminController {

    private final UserService userService;

    //ED-86-SA
    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    //ED-86-SA
    @GetMapping("/user-id/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    //ED-88-AA
    @GetMapping("/user-sub/{sub}")
    public ResponseEntity<UserDTO> getUserBySub(@PathVariable String sub) {
        return ResponseEntity.ok(userService.getUserBySUB(sub));
    }

    //ED-239-AWS
    @PostMapping("/admin/create-user")
    public ResponseEntity<UserDTO> createUserAsAdmin(@RequestBody CreateUserDTO dto){
        UserDTO created = userService.createUserAsAdmin(dto);
        return ResponseEntity.status(201).body(created);
    }
}
