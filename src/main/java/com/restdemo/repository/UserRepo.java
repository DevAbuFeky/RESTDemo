package com.restdemo.repository;

import com.restdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByUsernameAndPassword(String username, String password);

    User findFirstByUsername(String username);

    Optional<User> findByEmail(String email);
}
