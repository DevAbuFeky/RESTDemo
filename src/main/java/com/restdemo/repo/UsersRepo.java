package com.restdemo.repo;

import com.restdemo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
