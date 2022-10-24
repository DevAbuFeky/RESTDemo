package com.restdemo.services;


import com.restdemo.domain.Users;
import com.restdemo.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServices {
    @Autowired
    private UsersRepo usersRepo;

    public List<Users> getAllUsers(){
       return usersRepo.findAll();
    }

    public void addUser(Users users){
        usersRepo.save(users);
    }

    public void removeUser(int id){
        usersRepo.deleteById(id);
    }

    public Optional<Users> getUserById(int id){
        return usersRepo.findById(id);
    }


}
