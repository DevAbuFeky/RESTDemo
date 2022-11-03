package com.restdemo.repo;

import com.restdemo.domain.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DbInit implements CommandLineRunner {
    private UsersRepo usersRepo;
    private PasswordEncoder passwordEncoder;

    public DbInit(UsersRepo usersRepo, PasswordEncoder passwordEncoder){
        this.usersRepo = usersRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args){
        //Delete all
        this.usersRepo.deleteAll();

        //create users
        User normal = new User("feky",passwordEncoder.encode("feky123"),"USER","");
        User manager = new User("manager",passwordEncoder.encode("manager123"),"MANAGER","users");
        User admin = new User("admin",passwordEncoder.encode("admin123"),"ADMIN","users");

        List<User> users = Arrays.asList(normal,manager,admin);

        //save to DB
        this.usersRepo.saveAll(users);
    }
}
