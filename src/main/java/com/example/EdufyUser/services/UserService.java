package com.example.EdufyUser.services;

import com.example.EdufyUser.models.DTO.UserDTO;

public interface UserService {

    UserDTO getUserById(Long id);//ED-86-SA

    UserDTO getUserBySUB(String sub); //ED-88-AA
}
