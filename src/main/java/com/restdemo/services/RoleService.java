package com.restdemo.services;

import com.restdemo.model.security.Role;
import com.restdemo.repository.RoleRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepo roleRepo;

    private static final Logger LOG = LoggerFactory.getLogger(RoleService.class);

    public Role saveRole(Role role){
        log.info("Saving new user {} to the database" , role.getName());
        return roleRepo.save(role);
    }

    //to create local roles using commandLineRunner
    @Transactional
    public Role createRole(Role role) throws Exception{
        Role localRole = roleRepo.findByName(role.getName());

        if(localRole != null) {
            LOG.info("user {} already exists. Nothing will be done", role.getName());
        }else {
            localRole = roleRepo.save(role);
        }

        return localRole;
    }
}
