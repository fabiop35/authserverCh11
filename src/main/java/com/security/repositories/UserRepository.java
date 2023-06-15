package com.security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.entities.User;


public interface UserRepository
    extends JpaRepository<User, String> {

  Optional<User>
        findUserByUsername(String username);

//public User findByPassword(String password);

}
