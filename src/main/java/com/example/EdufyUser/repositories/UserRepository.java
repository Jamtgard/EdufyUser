package com.example.EdufyUser.repositories;

import com.example.EdufyUser.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByIsActiveTrue();//ED-87-SA
}
