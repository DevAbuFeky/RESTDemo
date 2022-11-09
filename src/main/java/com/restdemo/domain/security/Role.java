package com.restdemo.domain.security;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Id;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
//@Data
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;
    private String name;

    @OneToMany(mappedBy = "role", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Set<UserRole> userRoles = new HashSet<>();
}

