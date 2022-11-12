package com.restdemo.repository;

import com.restdemo.model.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Long> {

    Role findByName(String roleName);
}
