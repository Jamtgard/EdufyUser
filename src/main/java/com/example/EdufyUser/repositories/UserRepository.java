package com.example.EdufyUser.repositories;

import com.example.EdufyUser.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //ED-88-AA
    Optional<User> findByUuid(String sub);
}
