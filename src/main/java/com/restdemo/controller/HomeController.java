package com.restdemo.controller;

import com.restdemo.domain.Users;
import com.restdemo.exceptionhandle.ControlExceptionHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

//@Controller
@RestController
@RequestMapping("/api")
public class HomeController {

    @GetMapping({"/","/home"})
    public String home(){
//        return "Hello Sir ..!";
        return "Hello Sir ...!";
    }
}
