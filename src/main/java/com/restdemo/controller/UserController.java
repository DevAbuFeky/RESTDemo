package com.restdemo.controller;

import com.restdemo.domain.User;
import com.restdemo.services.UsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@RestController
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UsersServices usersServices;

    //Restcontroller Code

//    @GetMapping("/index")
//    public String index(){
//        return "users/index";
//    }
//    @GetMapping("/addUser")
//    public String addUser(){
//        return "users/addUser";
//    }
//
//    @GetMapping("/users")
//    public List<User> getUsers(Model model){
//        List<User> users = usersServices.getAllUsers();
//        model.addAttribute("user",users);
//        return users;
//    }
//    @GetMapping("/users/{userId}")
//    public Optional<User> getUser(@PathVariable Long userId){
//        Optional<User> users = usersServices.findById(Math.toIntExact(userId));
//
//        //isPresent used when object is optional
//        if (!users.isPresent()){
//            throw new ControlExceptionHandler("User ID not found - " + userId);
//        }
//
//        return users;
//    }
//
//    @PostMapping("/users")
//    public User addUser(@RequestBody User user){
//        user.setId(0);
//        usersServices.save(user);
//        return user;
//    }
//
//    @PutMapping("/users")
//    public User updateUser(@RequestBody User user){
//        usersServices.save(user);
//        return user;
//    }
//
//    @DeleteMapping("/users/{userId}")
//    public String users(@PathVariable Long userId){
//        Optional<User> users = usersServices.findById(Math.toIntExact(userId));
//
//        if (!users.isPresent()){
//            throw new ControlExceptionHandler("User ID not found - " + userId);
//        }
//
//        usersServices.removeUser(Math.toIntExact(userId));
//        return "Deleted ID - " + userId;
//    }

//    @GetMapping ("/access_denied")
//    public String errorLogin(){
//        return "access_denied";
//    }

    //Controller Code
    @GetMapping({"/index"})
    public String users(Model model){
        List<User> userList = usersServices.getAllUsers();
        model.addAttribute("userList", userList);
        return "users/index";
    }

    @GetMapping("/addUser")
    public String getUserAdd(Model model){
        model.addAttribute("users",new User());
        return "users/addUser";
    }

    @PostMapping("/addUser")
    public String posUsersAdd(@ModelAttribute("users") User user){
        usersServices.addUser(user);
        return "redirect:/users/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUsers(@PathVariable int id){
        usersServices.removeUser(id);
        return "redirect:/users/index";
    }

    @GetMapping("/update/{id}")
    public String updateUser(@PathVariable int id, Model model){
        Optional<User> users = usersServices.findById(id);
        if (users.isPresent()){
            model.addAttribute("users", users.get());
            return "users/addUser";
        }else {
            return "404";
        }
    }
}
