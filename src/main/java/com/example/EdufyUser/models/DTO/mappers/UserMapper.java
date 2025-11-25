package com.example.EdufyUser.models.DTO.mappers;

import com.example.EdufyUser.models.DTO.UserDTO;
import com.example.EdufyUser.models.entities.User;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

//ED-86-SA
public class UserMapper {

    //ED-86-SA
    //ED-318-SA: removed that user can be a creator
    public static UserDTO toDTONoId(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }

    //ED-86-SA
    public static UserDTO toDTOWithIdAndUUID(User user) {
        UserDTO userDTO = toDTONoId(user);
        userDTO.setId(user.getId());
        userDTO.setUuid(user.getUuid());
        userDTO.setActive(user.isActive());
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

    //ED-88-AA
    //ED-318-SA: removed that user can be a creator
    public static UserDTO toFullDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUuid(user.getUuid());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setActive(user.isActive());
        return dto;
    }

    //ED-340-AA
    public static UserDTO toDTOClientCallJustSUB(User user) {
        UserDTO dto = new UserDTO();
        dto.setUuid(user.getUuid());
        return dto;
    }

    //ED-346-AA
    public static UserDTO toDTOClientCallJustId(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        return dto;
    }
}
