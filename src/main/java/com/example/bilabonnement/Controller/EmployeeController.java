package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.Employee;
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

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    Service service;

    @GetMapping("/")                //Login page
    public String index(){
        return "index.html";
    }
    @GetMapping("/employeeDashboard")
    public String employeedashboard(){
        return "employeedashboard.html";
    }

    @GetMapping("/employeeform")
    public String employeeForm(){
        return "employeeform.html";
    }
    @PostMapping("/addEmployee")
    public String addEmployee(){
        return "redirect:/employeedashboard";
    }

    @PostMapping("/removeEmployee")
    public String removeEmployee(){
        return "redirect:/employeedashboard";
    }

    @PostMapping("/editEmployee")
    public String editEmployee(){
        return "redirect:/employeeform";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Employee loginInfo, WebRequest wr, HttpSession session){
        loginInfo.setEmail(wr.getParameter("email"));
        loginInfo.setPassword(wr.getParameter("password"));
        Integer tempUserID = service.userVerification(loginInfo);
        if (tempUserID != null) {
            System.out.println("yay");
            Employee tempEmp = service.getEmployeeByID(tempUserID);
            session.setAttribute("currentUser", tempEmp);
            return "redirect:/employeedashboard";
        }
        return "redirect:/";
    }
    @GetMapping("/employeedashboard")
    public String index(HttpSession session){
        if (session.getAttribute("currentUser") != null){
            return "/employeedashboard";
        }
        return "/index";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
