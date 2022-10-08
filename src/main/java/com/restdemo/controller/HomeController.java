package com.restdemo.controller;

import com.restdemo.domain.Users;
import com.restdemo.exceptionhandle.ControlExceptionHandler;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping({"/","/home"})
    public String home(){
        return "Hello Sir ..!";
    }
}
