package com.example.EdufyUser.controllers;

import com.example.EdufyUser.models.enums.MediaType;
import com.example.EdufyUser.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

//ED-89-AA
@RestController
@RequestMapping("/api/v1/user")
public class ClientController {

    private final UserService userService;

    public ClientController(UserService userService) {
        this.userService = userService;
    }

    //ED-89-AA
    @GetMapping("/history/{mediaType}/{userId}")
    public ResponseEntity<Set<Long>> getUserHistory(@PathVariable("mediaType") MediaType mediaType, @PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserHistoryByMediaType(mediaType, userId));
    }

}
