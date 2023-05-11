package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Component.BasicInfoComponent;
import com.example.bilabonnement.Service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LeaseController {
    @Autowired
    Service service;

    @Autowired
    BasicInfoComponent basicInfo;

    @GetMapping("/leaseDashboard")
    public String leaseDashboard(){
        return "leasedashboard.html";
    }

    @PostMapping("/leaseForm")
    public String leaseForm(){
        return "leaseform.html";
    }

    @PostMapping("/addLease")
    public String addLease(){
        return "redirect:/leasedashboard";
    }

    @PostMapping("/removeLease")
    public String removeLease(){
        return "redirect:/leasedashboard";
    }

    @PostMapping("/editLease")
    public String editLease(){
        return "redirect:/leaseform";
    }

}
