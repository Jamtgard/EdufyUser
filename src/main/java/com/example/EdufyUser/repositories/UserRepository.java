package com.example.EdufyUser.repositories;

import com.example.EdufyUser.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByActiveTrue();//ED-87-SA

    //ED-88-AA
    Optional<User> findByUuid(String sub);
}
