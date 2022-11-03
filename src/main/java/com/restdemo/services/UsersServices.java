package com.restdemo.services;


import com.restdemo.domain.User;
import com.restdemo.domain.security.UserRole;
import com.restdemo.repo.RoleRepository;
import com.restdemo.repo.UsersRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UsersServices {
    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private RoleRepository roleRepository;

    private static final Logger LOG = LoggerFactory.getLogger(UsersServices.class);


    public List<User> getAllUsers(){
       return usersRepo.findAll();
    }

    public User save(User user){
        return usersRepo.save(user);
    }


    public void removeUser(int id){
        usersRepo.deleteById(id);
    }

    public Optional<User> getUserById(int id){
        return usersRepo.findById(id);
    }

    public User createUser(User user, Set<UserRole> userRoles) throws Exception{
        User localUser = usersRepo.findByUsername(user.getUsername());

        if(localUser != null) {
            LOG.info("user {} already exists. Nothing will be done", user.getUsername());
        }else {
            for (UserRole ur : userRoles){
                roleRepository.save(ur.getRole());
            }

            user.getUserRole().addAll(userRoles);

            localUser = usersRepo.save(user);
        }

        return localUser;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersRepo.findByUsername(username);

        if(null == user) {
            throw new UsernameNotFoundException("Username not found");
        }

        return user;
    }


}
