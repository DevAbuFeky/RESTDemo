package com.restdemo.controller;

import com.restdemo.domain.Users;
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
    public List<Users> getUsers(){
        List<Users> users = usersServices.getAllUsers();
        return users;
    }

    @GetMapping("/users/{userId}")
    public Optional<Users> getUser(@PathVariable int userId){
        Optional<Users> users = usersServices.getUserById(userId);

        //isPresent used when object is optional
        if (!users.isPresent()){
            throw new ControlExceptionHandler("User ID not found - " + userId);
        }

        return users;
    }

    @PostMapping("/users")
    public Users addUser(@RequestBody Users users){
        users.setId(0);
        usersServices.addUser(users);
        return users;
    }

    @PutMapping("/users")
    public Users updateUser(@RequestBody Users users){
        usersServices.addUser(users);
        return users;
    }

    @DeleteMapping("/users/{userId}")
    public String users(@PathVariable int userId){
        Optional<Users> users = usersServices.getUserById(userId);

        if (!users.isPresent()){
            throw new ControlExceptionHandler("User ID not found - " + userId);
        }

        usersServices.removeUser(userId);
        return "Deleted ID - " + userId;
    }

    @GetMapping ("/access_denied")
    public String errorLogin(){
        return "access_denied";
    }
}
