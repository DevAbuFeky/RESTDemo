package com.restdemo;

import com.restdemo.model.User;
import com.restdemo.model.security.Role;
import com.restdemo.services.RoleService;
import com.restdemo.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class RestDemoApplication implements CommandLineRunner {

    private final UserService userService;
    private final RoleService roleService;

    public RestDemoApplication(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    public static void main(String[] args) {
        SpringApplication.run(RestDemoApplication.class, args);
    }

    public void run(String... args) throws Exception{

        //Adding Roles
        roleService.createRole(new Role(null,"ROLE_USER"));
        roleService.createRole(new Role(null,"ROLE_ADMIN"));
        roleService.createRole(new Role(null,"ROLE_MANAGER"));
        roleService.createRole(new Role(null,"ROLE_SUPER_ADMIN"));

        //adding local user
        userService.createUser(new User(null,"Admin", "Panel","adminUser","admin@gmail.com"
                ,"password",true, new ArrayList<>()));
        userService.createUser(new User(null,"Normal", "User","normalUser","user@gmail.com"
                ,"password",true, new ArrayList<>()));

        //adding role to local user
        userService.addRoleToUser("adminUser","ROLE_ADMIN");
//        userService.addRoleToUser("adminUser","ROLE_MANAGER");
        userService.addRoleToUser("normalUser","ROLE_USER");
    }
}
