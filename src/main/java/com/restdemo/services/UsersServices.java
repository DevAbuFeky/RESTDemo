package com.restdemo.services;


import com.restdemo.domain.User;
import com.restdemo.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServices {
    @Autowired
    private UsersRepo usersRepo;

    public List<User> getAllUsers(){
       return usersRepo.findAll();
    }

    public void addUser(User user){
        usersRepo.save(user);
    }

    public void removeUser(int id){
        usersRepo.deleteById(id);
    }

    public Optional<User> getUserById(int id){
        return usersRepo.findById(id);
    }


}
