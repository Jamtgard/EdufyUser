package com.example.EdufyUser.models.DTO.mappers;

import com.example.EdufyUser.models.DTO.UserDTO;
import com.example.EdufyUser.models.entities.User;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

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
    public static UserDTO toFullDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUuid(user.getUuid());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setCreator(user.isCreator());
        dto.setActive(user.isActive());
        return dto;
    }
}
