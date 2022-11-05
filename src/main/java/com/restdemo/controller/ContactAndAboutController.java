package com.restdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ContactAndAboutController {

    @GetMapping("contact")
    public String contactUs(){
        return "404";
    }

    @GetMapping("about")
    public String aboutUs(){
        return "404";
    }
}
