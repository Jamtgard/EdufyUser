package com.example.EdufyUser.controllers;

import com.example.EdufyUser.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//ED-89-AA
@RestController
@RequestMapping("/user")
@PreAuthorize("hasRole('microservice_access')") //ED-340-AA
public class ClientController {

    private final UserService userService;

    public ClientController(UserService userService) {
        this.userService = userService;
    }

}
