package com.restdemo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(nullable = false, updatable = false)
    private long id;

//    @Column(name = "first_name")
//    private String firstName;
//    @Column(name = "last_name")
//    private String lastName;

    @Column(nullable = false)
    private String userName;

//    @Column(name = "email", nullable = false)
//    private String email;

    @Column(nullable = false)
    private String password;

    private int active;

    private String roles = "";

    private String permissions = "";

    public User(String userName, String password, String roles, String permissions) {
//        this.firstName = firstName;
//        this.lastName = lastName;
        this.userName = userName;
//        this.email = email;
        this.password = password;
        this.roles = roles = "";
        this.permissions = permissions = "";
        this.active = 1;
    }

    protected User(){}

    public int getActive() {
        return active;
    }

    public List<String> getRoleList(){
        if(this.roles.length() > 0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public List<String> getPermissionList(){
        if(this.permissions.length() > 0){
            return Arrays.asList(this.permissions.split(","));
        }
        return new ArrayList<>();
    }

}
