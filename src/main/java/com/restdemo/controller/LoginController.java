package com.restdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
//@RestController
@RequestMapping("/")
public class LoginController {

    @GetMapping("/loginPage")
    public String loginPage(){
        return "login";
    }
}
