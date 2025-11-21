package com.example.EdufyUser.services;

import com.example.EdufyUser.convertes.Roles;
import com.example.EdufyUser.exceptions.ContentNotFoundException;
import com.example.EdufyUser.exceptions.ResourceNotFoundException;
import com.example.EdufyUser.models.DTO.CreateUserDTO;
import com.example.EdufyUser.models.DTO.UserDTO;
import com.example.EdufyUser.models.DTO.mappers.UserMapper;
import com.example.EdufyUser.models.entities.User;
import com.example.EdufyUser.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final KeycloakAdminService keycloakAdminService;

    //ED-86-SA
    @Autowired
    public UserServiceImpl(UserRepository userRepository, KeycloakAdminService keycloakAdminService) {
        this.userRepository = userRepository;
        this.keycloakAdminService = keycloakAdminService;
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

    //ED-87-SA // will only allow users with role "user_admin" get all users, no matter if inactive or not
    @Override
    public List<UserDTO> getAllUsers(Authentication authentication) {
        List<User> allUsers;

        if(Roles.getRoles(authentication).contains("user_admin")){
            allUsers = userRepository.findAll();
            listUserEmpty(allUsers);
            return UserMapper.toDTOWithIdAndUUIDList(allUsers);

        }else {
            allUsers = userRepository.findAllByActiveTrue();
            listUserEmpty(allUsers);
            return UserMapper.toDTOWNoIdList(allUsers);
        }

    }

    private void listUserEmpty(List<User> list){
        if(list.isEmpty()){
            throw new ContentNotFoundException("No users found");
        }
    }

    //ED-88-AA
    @Override
    public UserDTO getUserBySUB(String sub, Authentication auth) {
        User user = userRepository.findByUuid(sub).orElseThrow(() ->
                new ResourceNotFoundException("User","sub",sub));

        if (auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_microservice_access"))) {
            return UserMapper.toDTOClientCallJustSUB(user);
        }
        return UserMapper.toFullDTO(user);
    }

    //ED-239-AWS
    @Override
    public UserDTO createUserAsAdmin(CreateUserDTO createUserDTO) {
        String keycloakId = keycloakAdminService.createUserAndAssignRoles(createUserDTO);

        boolean active = createUserDTO.getActive() == null || createUserDTO.getActive();

        User user = new User(
                keycloakId,
                createUserDTO.getUsername(),
                createUserDTO.getEmail(),
                createUserDTO.isCreator(),
                active
        );

        User savedUser = userRepository.save(user);

        return UserMapper.toDTOWithIdAndUUID(savedUser);
    }

}
