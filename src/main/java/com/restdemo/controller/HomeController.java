package com.restdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
//@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping({"/","/index"})
    public String index(){
        return "index";
    }
}
