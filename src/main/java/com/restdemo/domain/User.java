package com.restdemo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restdemo.domain.security.UserRole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private int id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    @Column(name = "email", nullable = false, updatable = false)
    private String email;
    private String phone;
    private boolean active = true;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<UserRole> userRole = new HashSet<>();

    public User() {}

//    public List<String> getRoleList(){
//        if(this.roles.length() > 0){
//            return Arrays.asList(this.roles.split(","));
//        }
//        return new ArrayList<>();
//    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

}

