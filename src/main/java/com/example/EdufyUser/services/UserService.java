package com.example.EdufyUser.services;

import com.example.EdufyUser.models.DTO.UserDTO;
import org.springframework.security.core.Authentication;

import java.util.List;


public interface UserService {

    UserDTO getUserById(Long id);//ED-86-SA
    List<UserDTO> getAllUsers(Authentication authentication);//ED-87-SA

    UserDTO getUserBySUB(String sub); //ED-88-AA

}
