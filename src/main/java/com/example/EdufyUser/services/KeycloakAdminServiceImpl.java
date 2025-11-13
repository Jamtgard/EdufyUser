package com.example.EdufyUser.services;

import com.example.EdufyUser.configs.RestTemplateConfig;
import com.example.EdufyUser.models.DTO.CreateUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

//ED-239-AWS
@Service
public class KeycloakAdminServiceImpl implements KeycloakAdminService {

    private final RestTemplateConfig restTemplateConfig;



    @Autowired
    public KeycloakAdminServiceImpl(RestTemplateConfig restTemplateConfig) {
        this.restTemplateConfig = restTemplateConfig;
    }

    @Override
    public String createUserAndAssignRole(CreateUserDTO createUserDTO) {
        return "";
    }
}
