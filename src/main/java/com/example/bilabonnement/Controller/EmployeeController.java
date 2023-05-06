package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {
    @Autowired
    Service service;

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
}
