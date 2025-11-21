package com.example.EdufyUser.services;

import com.example.EdufyUser.models.DTO.CreateUserDTO;
import com.example.EdufyUser.models.DTO.UserDTO;
import org.springframework.security.core.Authentication;

import java.util.List;


public interface UserService {

    UserDTO getUserById(Long id, Authentication auth);//ED-86-SA //ED-346-AA
    List<UserDTO> getAllUsers(Authentication authentication);//ED-87-SA

    UserDTO getUserBySUB(String sub, Authentication auth); //ED-88-AA //ED-340-AA

//ED-239-AWS
    UserDTO createUserAsAdmin(CreateUserDTO createUserDTO);

}
