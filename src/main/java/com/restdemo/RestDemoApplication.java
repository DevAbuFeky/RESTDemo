package com.restdemo;

import com.restdemo.domain.User;
import com.restdemo.domain.security.Role;
import com.restdemo.domain.security.UserRole;
import com.restdemo.securityConfig.SecurityUtility;
import com.restdemo.services.UsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class RestDemoApplication implements CommandLineRunner {

    @Autowired
    private UsersServices usersServices;


    public static void main(String[] args) {
        SpringApplication.run(RestDemoApplication.class, args);
    }

    public void run(String... args) throws Exception{
        User user1 = new User();
        user1.setFirstName("admin");
        user1.setLastName("admin");
        user1.setUsername("admin");
        user1.setPassword(SecurityUtility.passwordEncoder().encode("password"));
        user1.setEmail("admin@gmail.com");
        Set<UserRole> userRoles = new HashSet<>();
        Role role1 = new Role();
        role1.setRoleId(1);
        role1.setName("ROLE_ADMIN");
        userRoles.add(new UserRole(user1, role1));

        usersServices.createUser(user1,userRoles);
    }
}
