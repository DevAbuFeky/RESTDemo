package com.restdemo.services;


import com.restdemo.domain.User;
import com.restdemo.domain.security.PasswordResetToken;
import com.restdemo.domain.security.UserRole;
import com.restdemo.repo.PasswordResetTokenRepository;
import com.restdemo.repo.RoleRepository;
import com.restdemo.repo.UsersRepo;
import com.restdemo.services.principal.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UsersServices {
    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;


    private static final Logger LOG = LoggerFactory.getLogger(UsersServices.class);


    public List<User> getAllUsers(){
       return usersRepo.findAll();
    }

    public User addUser(User user){
        return usersRepo.save(user);
    }

    public User save(User user){
        return usersRepo.save(user);
    }


    public void removeUser(int id){
        usersRepo.deleteById(Math.toIntExact(id));
    }

    public Optional<User> findById(int id) {
        return usersRepo.findById(id);
    }

    public User findByUsername(String username){
        return usersRepo.findByUsername(username);
    }

    public User findByEmail(String email){
        return usersRepo.findByEmail(email);
    }


    public PasswordResetToken getPasswordResetToken(final String token){
        return passwordResetTokenRepository.findByToken(token);
    }

    @Transactional
    public User createUser(User user, Set<UserRole> userRoles) {
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

    public void createPasswordResetTokenForUser(final User user, final String token){
        final PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordResetTokenRepository.save(myToken);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersRepo.findByUsername(username);
        UserPrincipal userPrincipal = new UserPrincipal(user);

        if(null == user) {
            throw new UsernameNotFoundException("Username not found");
        }

        return userPrincipal;
    }

}
