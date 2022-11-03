package com.restdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestDemoApplication{
//        implements CommandLineRunner{

//    @Autowired
//    private UsersServices userService;


    public static void main(String[] args) {
        SpringApplication.run(RestDemoApplication.class, args);

    }

//    @Override
//    public void run(String... args) throws Exception{
//        Users user1 = new Users();
//        user1.setUsername("admin");
//        user1.setPassword(SecurityUtility.passwordEncoder().encode("password"));
//        user1.setEmail("admin@gmail.com");
//        Set<UserRole> userRoles = new HashSet<>();
//        Role role1 = new Role();
//        role1.setRoleId(1);
//        role1.setName("ROLE_ADMIN");
//        userRoles.add(new UserRole(user1, role1));
//
//        userService.createUser(user1, userRoles);
//    }


}
