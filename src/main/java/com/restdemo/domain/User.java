package com.restdemo.domain;

import com.restdemo.domain.security.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@Data
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false, updatable = false)
    private int id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    @Column(name = "email")
//            , nullable = false, updatable = false)
    private String email;
    private String phone;

    private boolean active = true;


    @OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinTable(name = "roles_user",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {}


    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

}

