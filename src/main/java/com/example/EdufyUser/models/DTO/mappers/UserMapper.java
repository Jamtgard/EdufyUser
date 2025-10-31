package com.example.EdufyUser.models.DTO.mappers;

import com.example.EdufyUser.models.DTO.UserDTO;
import com.example.EdufyUser.models.entities.User;

import java.util.ArrayList;
import java.util.List;

//ED-86-SA
public class UserMapper {

    //ED-86-SA
    public static UserDTO toDTONoId(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setCreator(userDTO.isCreator());
        return userDTO;
    }

    //ED-86-SA
    public static UserDTO toDTOWithIdAndUUID(User user) {
        UserDTO userDTO = toDTONoId(user);
        userDTO.setId(user.getId());
        userDTO.setUuid(user.getUuid());
        return userDTO;
    }

    //ED-87-SA
    public static List<UserDTO> toDTOWithIdAndUUIDList(List<User> users) {
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(toDTOWithIdAndUUID(user));
        }
        return userDTOs;
    }

    //ED-87-SA
    public static List<UserDTO> toDTOWNoIdList(List<User> users) {
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(toDTONoId(user));
        }
        return userDTOs;
    }
}
