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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class DamageReportController {

    @Autowired
    Service service;

    @GetMapping("/damageReportForm")
    public String damageReportForm(){

        return "editdamagereport.html";
    }


    @PostMapping("/deleteDamageReport")
    public String deleteDamageReport(WebRequest wr){
    service.deleteDamageReportByID(Integer.parseInt(wr.getParameter("reportID")));
        return "redirect:/damageReportDashboard";
    }

    @PostMapping("/editDamageReport")
    public String editDamageReport(WebRequest wr, Model model){
    int tempID = Integer.parseInt(wr.getParameter("reportID"));
    DamageReport tempDam = service.getDamageReportByID(tempID);
    model.addAttribute("damageReport", tempDam);

        return "editdamagereport.html";
    }
    @GetMapping("/damageReportDashboard")
        public String getDamagereports(Model model, @ModelAttribute Employee employee, HttpSession session){
            Employee tempEmp = (Employee) session.getAttribute("currentUser");
            model.addAttribute(tempEmp);
            List<DamageReport> damageReports = service.fetchAllDamageReports();
            model.addAttribute("damageReports", damageReports);
        return "damagereportdashboard.html";
    }

    @PostMapping("/addDamageReport")
    public String addDamageReport(Model model, @ModelAttribute DamageReport damageReport, WebRequest wr,
                                  HttpSession session) {
        Employee tempEmp = (Employee) session.getAttribute("currentUser");
        model.addAttribute(tempEmp);
        damageReport.setDescription(wr.getParameter("description"));
        damageReport.setLeaseID(Integer.parseInt(wr.getParameter("lease_id")));
        damageReport.setEmployeeID(tempEmp.getEmployeeID());
        damageReport.setCost(Double.parseDouble(wr.getParameter("cost")));
        damageReport.setDateAccident(wr.getParameter("date_accident"));
        damageReport.setDateReport(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        service.addDamageReport(damageReport);
        return "redirect:/damageReportDashboard";
    }

}


