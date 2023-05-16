package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.Car;
import com.example.bilabonnement.Model.Employee;
import com.example.bilabonnement.Model.Lease;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public String login(@ModelAttribute Employee loginInfo, WebRequest wr, HttpSession session, Model model){
        loginInfo.setEmail(wr.getParameter("email"));
        loginInfo.setPassword(wr.getParameter("password"));
        Integer tempUserID = service.userVerification(loginInfo);
        if (tempUserID != null) {
            Employee tempEmp = service.getEmployeeByID(tempUserID);
            List<Object[]> tempMergedList = service.getMergedList();
            session.setAttribute("currentUser", tempEmp);
            model.addAttribute("mergedList", tempMergedList);
            model.addAttribute("currentTab", "all");
            return "redirect:/maindashboard";
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
