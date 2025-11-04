package com.example.EdufyUser.services;

import com.example.EdufyUser.models.DTO.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO getUserById(Long id);//ED-86-SA
    List<UserDTO> getAllUsers();//ED-87-SA
}
