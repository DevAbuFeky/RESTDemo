package com.restdemo.api;

import com.restdemo.model.User;
import com.restdemo.model.security.Role;
import com.restdemo.repository.RoleRepo;
import com.restdemo.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class RoleController {

    private final RoleRepo roleRepo;
    private final RoleService roleService;

    @GetMapping({"/roles"})
    public ResponseEntity<List<Role>> getRoles(){
        return ResponseEntity.ok().body(roleRepo.findAll());
    }

    @PostMapping("/roles/save")
    public ResponseEntity<Role> saveUser(@RequestBody Role role){
        return ResponseEntity.ok().body(roleService.saveRole(role));
    }
}
