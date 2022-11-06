package com.restdemo.controller;

import com.restdemo.domain.User;
import com.restdemo.domain.security.PasswordResetToken;
import com.restdemo.domain.security.Role;
import com.restdemo.domain.security.UserRole;
import com.restdemo.securityConfig.SecurityUtility;
import com.restdemo.services.UsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("profile")
public class ProfileController {

    @Autowired
    private UsersServices usersServices;

    @GetMapping("index")
    public String index(){
        return "profile/index";
    }

    @RequestMapping("/index")
    public String myProfile(Model model, Principal principal){

        User user = usersServices.findByUsername(principal.getName());

        model.addAttribute("user", user);
        model.addAttribute("classActiveEdit", true);

        return "profile/index";
    }

    //adding new user
    @PostMapping("/newUser")
    public String newUserPost(HttpServletRequest request,
                              @ModelAttribute("email") String userEmail,
                              @ModelAttribute("username") String username,
                              @ModelAttribute("password") String password,
                              Model model) throws Exception{

        model.addAttribute("classActiveNewAccount", true);
        model.addAttribute("email",userEmail);
        model.addAttribute("username", username);

        if (usersServices.findByUsername(username) != null){
            model.addAttribute("usernameExists", true);

            return "login";
        }

        if (usersServices.findByEmail(userEmail) != null){
            model.addAttribute("emailExists", true);

            return "login";
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(userEmail);
        user.setPassword(password);

        String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
        user.setPassword(encryptedPassword);

        Role role = new Role();
        role.setRoleId(2);
        role.setName("ROLE_USER");
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(new UserRole(user,role));
        usersServices.createUser(user, userRoles);

        String token = UUID.randomUUID().toString();
        usersServices.createPasswordResetTokenForUser(user, token);

        return "login";

    }

    //adding new user
    @GetMapping("/newUser")
    public String newUser(Locale locale, @RequestParam("token") String token, Model model) {
        PasswordResetToken passToken = usersServices.getPasswordResetToken(token);

        if(passToken == null){
            String message = "Invalid Token.";
            model.addAttribute("message", message);
            return "redirect:/badRequest";
        }

        User user = passToken.getUser();
        String username = user.getUsername();

        UserDetails userDetails = usersServices.loadUserByUsername(username);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        model.addAttribute("user", user);

        model.addAttribute("classActiveEdit", true);

        return "login";
    }


    //update user info
    @PutMapping( "/updateUserInfo")
    public String updateUserInfo (@ModelAttribute("user") User user,
                                  @ModelAttribute("newPassword") String newPassword,
                                  Model model) throws Exception{

        Optional<User> currentUser = usersServices.findById(user.getId());

        if (currentUser == null){
            throw new Exception("User not found");
        }

        if(currentUser.isPresent()){

//        check email already exists
            if (usersServices.findByEmail(user.getEmail()) != null){
                if (usersServices.findByEmail(user.getEmail()).getId() != currentUser.get().getId()){
                    model.addAttribute("emailExists", true);
                    return "profile/index";
                }
            }

            //        check username already exists
            if (usersServices.findByUsername(user.getUsername()) != null){
                if (usersServices.findByUsername(user.getUsername()).getId() != currentUser.get().getId()){
                    model.addAttribute("usernameExists", true);
                    return "profile/index";
                }
            }

            // update password --remove && !newPassword.equals("") > Condition '!newPassword.equals("")' is always 'true'
            if (newPassword != null && !newPassword.isEmpty()){
                BCryptPasswordEncoder passwordEncoder = SecurityUtility.passwordEncoder();
                String dbPassword = currentUser.get().getPassword();
                if (passwordEncoder.matches(user.getPassword(), dbPassword)){
                    currentUser.get().setPassword(passwordEncoder.encode(newPassword));
                }else {
                    model.addAttribute("incorrectPassword", true);

                    return "profile/index";
                }
            }
        }

        currentUser.get().setFirstName(user.getFirstName());
        currentUser.get().setLastName(user.getLastName());
        currentUser.get().setUsername(user.getUsername());
        currentUser.get().setEmail(user.getEmail());

        usersServices.save(currentUser.get());

        model.addAttribute("updateSuccess", true);
        model.addAttribute("user", currentUser.get());
        model.addAttribute("classActiveEdit",true);

        UserDetails userDetails = usersServices.loadUserByUsername(currentUser.get().getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "profile/index";
    }

}
