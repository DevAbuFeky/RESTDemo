package com.restdemo.services.principal;

import com.restdemo.domain.User;
import com.restdemo.domain.security.Authority;
import com.restdemo.domain.security.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserPrincipal implements UserDetails {

    private User user;
    private Set<UserRole> userRole = new HashSet<>();;

    public UserPrincipal(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        userRole.forEach(ur -> authorities.add(new Authority(ur.getRole().getName())));

//        // Extract list of permissions (name)
//        this.user.getPermissionList().forEach(p -> {
//            GrantedAuthority authority = new SimpleGrantedAuthority(p);
//            authorities.add(authority);
//        });

//        // Extract list of roles (ROLE_name)
//        this.user.getRoleList().forEach(r -> {
//            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + r);
//            authorities.add(authority);
//        });

        return authorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.user.isActive();
    }
}
