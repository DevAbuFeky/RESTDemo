package com.restdemo.services.principal;

import com.restdemo.domain.User;
import com.restdemo.domain.security.Role;
import com.restdemo.repo.RoleRepository;
import com.restdemo.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersRepo.findByUsername(username);
        UserPrincipal userPrincipal = new UserPrincipal(user);

        if(null == user) {
            throw new UsernameNotFoundException("Username not found");
        }

        return userPrincipal;
    }

    public Role loadRoleName(String roleName){
        Role role = roleRepository.findByName(roleName);

        return role;
    }
}
