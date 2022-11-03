package com.restdemo.domain.security;

import com.restdemo.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
//@Data
@Table(name = "user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long userRoleId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="role_id")
    private Role role;

    public UserRole(){}

    public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
    }
}

