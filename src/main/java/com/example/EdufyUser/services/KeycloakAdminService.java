package com.example.EdufyUser.services;

import com.example.EdufyUser.models.DTO.CreateUserDTO;

//ED-239-AWS
public interface KeycloakAdminService {

    String createUserAndAssignRole(CreateUserDTO createUserDTO);
}
