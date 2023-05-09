package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DamageReportController {

    @Autowired
    Service service;

    @GetMapping("/damageReportForm")
    public String damageReportForm(){

        return "damagereportform.html";
    }

    @PostMapping("/removeDamageReport")
    public String removeDamageReport(){

        return "redirect:/customerdashboard";
    }

    @PostMapping("/editDamageReport")
    public String editDamageReport(){

        return "redirect:/damagereportform";
    }
    @GetMapping("/damageReportDashboard")
    public String damageReportDashboard(){
        return "damagereportdashboard.html";
    }

    @GetMapping("/addDamageReport")
    public String addDamageReport(){
        return "redirect:/damagereportDashboard";
    }
}


