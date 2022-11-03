package com.restdemo.controller;

import com.restdemo.domain.User;
import com.restdemo.exceptionhandle.ControlExceptionHandler;
import com.restdemo.services.UsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UsersServices usersServices;

    @GetMapping("/users")
    public List<User> getUsers(){
        List<User> users = usersServices.getAllUsers();
        return users;
    }

    @GetMapping("/users/{userId}")
    public Optional<User> getUser(@PathVariable int userId){
        Optional<User> users = usersServices.getUserById(userId);

        //isPresent used when object is optional
        if (!users.isPresent()){
            throw new ControlExceptionHandler("User ID not found - " + userId);
        }

        return users;
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user){
        user.setId(0L);
        usersServices.save(user);
        return user;
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user){
        usersServices.save(user);
        return user;
    }

    @DeleteMapping("/users/{userId}")
    public String users(@PathVariable int userId){
        Optional<User> users = usersServices.getUserById(userId);

        if (!users.isPresent()){
            throw new ControlExceptionHandler("User ID not found - " + userId);
        }

        usersServices.removeUser(userId);
        return "Deleted ID - " + userId;
    }

//    @GetMapping ("/access_denied")
//    public String errorLogin(){
//        return "access_denied";
//    }
}
