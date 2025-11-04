package com.example.EdufyUser.models.DTO.mappers;

import com.example.EdufyUser.models.DTO.UserDTO;
import com.example.EdufyUser.models.entities.User;

//ED-86-SA
public class UserMapper {

    //ED-86-SA
    public static UserDTO toDTONoId(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setCreator(user.isCreator());
        return userDTO;
    }

    //ED-86-SA
    public static UserDTO toDTOWithIdAndUUID(User user) {
        UserDTO userDTO = toDTONoId(user);
        userDTO.setId(user.getId());
        userDTO.setUuid(user.getUuid());
        userDTO.setActive(String.valueOf(user.isActive()));
        return userDTO;
    }
}
