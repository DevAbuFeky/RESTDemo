package com.restdemo.services;

import com.restdemo.model.User;
import com.restdemo.model.security.Role;
import com.restdemo.repository.RoleRepo;
import com.restdemo.repository.UserRepo;
import com.restdemo.services.principal.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    public List<User> getAllUsers(){
        log.info("Fetching all users");
        return userRepo.findAll();
    }

    public User addUser(User user){
        return userRepo.save(user);
    }

    public void removeUser(Long id){
        userRepo.deleteById(id);
    }

    //adding role to user
    public void addRoleToUser(String username,String roleName){

        log.info("Adding role {} to user {}" ,roleName , username);
        User user = userRepo.findFirstByUsername(username);
        Role role = roleRepo.findByName(roleName);

        if(user.getRoles().isEmpty()) {
            user.getRoles().add(role);
        }else {
            LOG.info("Nothing will be done in roles for user {}", user.getUsername());
        }
    }

    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }

    public User findByUsername(String username){
        return userRepo.findFirstByUsername(username);
    }

    public Optional<User> findByEmail(String email){
        return userRepo.findByEmail(email);
    }

    public User save(User user){
        log.info("Saving new user {} to the database" , user.getUsername());
        return userRepo.save(user);
    }

    public User registerUser(String firstName,String lastName,String username , String email, String password){
        if (username == null || password == null) {
            return null;
        } else {
            if (userRepo.findFirstByUsername(username) != null){
                System.out.println("Duplicate Username or Email");
                return null;
            }
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);

            return userRepo.save(user);
        }
    }

    public User authenticate(String username, String password){
        return userRepo.findByUsernameAndPassword(username,password).orElse(null);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findFirstByUsername(username);

        if(null == user) {
            throw new UsernameNotFoundException("Username not found");
        }

        return new UserPrincipal(user);
    }

    //to create local user using commandLineRunner
    @Transactional
    public User createUser(User user) throws Exception{
        User localUser = userRepo.findFirstByUsername(user.getUsername());

        if(localUser != null) {
            LOG.info("user {} already exists. Nothing will be done", user.getUsername());
        }else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            localUser = userRepo.save(user);
        }

        return localUser;
    }
}
