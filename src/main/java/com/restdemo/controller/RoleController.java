package com.restdemo.controller;

import com.restdemo.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class RoleController {

    @GetMapping({"/role"})
    public String roles(){
        return "users/role";
    }
}
