package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {
    @Autowired
    Service service;

    @GetMapping("/customerDashboard")
    public String customerDashboard(){

        return "customerdashboard.html";
    }

    @GetMapping("/customerForm")
    public String customerForm(){

        return "customerform.html";
    }

    @PostMapping("/addCustomer")
    public String addCustomer(){

        return "redirect:/customerdashboard";
    }

    @PostMapping("/removeCustomer")
    public String removeCustomer(){

        return "redirect:/customerdashboard";
    }

    @PostMapping("/editCustomer")
    public String editCustomer(){

        return "redirect:/customerform";
    }
}
