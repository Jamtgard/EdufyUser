package com.example.EdufyUser.services;

import com.example.EdufyUser.exceptions.ResourceNotFoundException;
import com.example.EdufyUser.models.DTO.UserDTO;
import com.example.EdufyUser.models.DTO.mappers.UserMapper;
import com.example.EdufyUser.models.entities.User;
import com.example.EdufyUser.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    //ED-86-SA
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //ED-86-SA
    @Override
    public UserDTO getUserById(Long id) {
        Optional<User> findUser = userRepository.findById(id);
        if(findUser.isEmpty()){
            throw new ResourceNotFoundException("User","id",id);
        }
        return UserMapper.toDTOWithIdAndUUID(findUser.get());
    }
}
