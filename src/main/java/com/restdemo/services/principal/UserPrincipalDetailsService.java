package com.restdemo.services.principal;

import com.restdemo.model.User;
import com.restdemo.repository.UserRepo;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@Service("userDetailsService")
@Transactional
public class UserPrincipalDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    public UserPrincipalDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    //map the right set of authorities from the roles and privileges the user has assigned
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findFirstByUsername(username);
        if(null == user) {
            throw new UsernameNotFoundException("Username not found :" + username);
        }

        //looping over all the roles for user
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword()
                ,true, true, true, true, authorities);
    }
}
