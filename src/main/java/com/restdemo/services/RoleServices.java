package com.restdemo.services;

import com.restdemo.domain.User;
import com.restdemo.domain.security.Role;
import com.restdemo.repo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServices {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAllRoles(){
        return (List<Role>) roleRepository.findAll();
    }
}
