package com.example.EdufyUser.services;

import com.example.EdufyUser.models.DTO.UserDTO;
import com.example.EdufyUser.models.enums.MediaType;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Set;

public interface UserService {

    UserDTO getUserById(Long id);//ED-86-SA
    List<UserDTO> getAllUsers(Authentication authentication);//ED-87-SA

    UserDTO getUserBySUB(String sub); //ED-88-AA

    Set<Long> getUserHistoryByMediaType(MediaType mediaType, Long userId); //ED-89-AA
}
