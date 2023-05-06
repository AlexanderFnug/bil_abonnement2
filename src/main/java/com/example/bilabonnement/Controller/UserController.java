package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Service.Service;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    Service service;

    @GetMapping("/")                //Login page
    public String index(){
        return "index.html";
    }

    @PostMapping("/userDashboard")  //User dashboard
    public String userDashboard(){
        return "userdashboard.html";
    }
    @PostMapping("/userForm")       //User register page
    public String userForm(){
        return "userform.html";
    }

    @PostMapping("/addUser")
    public String addUser(){
        return ("redirect:/userdashboard");
    }

    @PostMapping("/removeUser")
    public String removeUser(){
        return ("redirect:/userdashboard");
    }

    @PostMapping("/editUser")
    public String editUser(){
        return ("redirect:/userform");
    }

    @PostMapping("/login")
    public String login(HttpSession session){
        return "redirect:/dashboard";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
