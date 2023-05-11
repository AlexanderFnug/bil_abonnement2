package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.DamageReport;
import com.example.bilabonnement.Model.Employee;
import com.example.bilabonnement.Service.Service;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

        public String getDamagereports(Model model, @ModelAttribute Employee employee, HttpSession session){
            Employee tempEmp = (Employee) session.getAttribute("currentUser");
            model.addAttribute(tempEmp);
            List<DamageReport> damageReports = service.fetchAllDamageReports();
            model.addAttribute("damageReports", damageReports);

        return "damagereportdashboard.html";
    }

    @GetMapping("/addDamageReport")
    public String addDamageReport(){
        return "redirect:/damagereportDashboard";
    }
}


