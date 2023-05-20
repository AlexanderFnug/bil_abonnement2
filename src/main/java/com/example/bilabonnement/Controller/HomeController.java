package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.Employee;
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
public class HomeController {
    @Autowired
    Service service;

    @GetMapping("/")                //Login page
    public String index(){
        return "index.html";
    }
    @GetMapping("/mainDashboard")
    public String mainDashboard(HttpSession session, Model model, WebRequest wr){
        if (session.getAttribute("currentUser") == null){
            return "redirect:/index";
        }
        model.addAttribute("mergedList", service.getMergedList());
        model.addAttribute("totalLeaseValue", service.getLeasedTotal());
        model.addAttribute("totalLeasedCars", service.getActiveLeases().size());

        if (wr.getParameter("changeTab") != null) {
            model.addAttribute("currentTab", wr.getParameter("changeTab"));
        } else {
            model.addAttribute("currentTab", "all");
        }

        return "maindashboard.html";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Employee loginInfo, WebRequest wr, HttpSession session, Model model){
        loginInfo.setEmail(wr.getParameter("email"));
        loginInfo.setPassword(wr.getParameter("password"));
        Integer tempUserID = service.userVerification(loginInfo);
        if (tempUserID != null) {
            Employee tempEmp = service.getEmployeeByID(tempUserID);
            session.setAttribute("currentUser", tempEmp);
            return "redirect:/mainDashboard";
        }
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
