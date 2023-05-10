package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.User;
import com.example.bilabonnement.Service.Service;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

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
    public String login(Model model, @ModelAttribute User loginInfo, WebRequest wr, HttpSession session){
        loginInfo.setUsername(wr.getParameter("username"));
        loginInfo.setPassword(wr.getParameter("password"));
        Integer tempUserID = service.validation(loginInfo);
        if (tempUserID != null){
            User tempUser = service.getUserByID(tempUserID);
            session.setAttribute("currentUser", tempUser); //Gets the most recent user(Last user in list)
            return "redirect:/userdashboard";
        }

        return ("redirect:/");
    }

    @PostMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
