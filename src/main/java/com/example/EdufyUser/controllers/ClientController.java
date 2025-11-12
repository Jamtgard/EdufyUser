package com.example.EdufyUser.controllers;

import com.example.EdufyUser.services.UserService;
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

}
