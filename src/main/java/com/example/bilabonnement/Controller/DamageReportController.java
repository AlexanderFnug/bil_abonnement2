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
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Controller class for handling damage reports.
 * Handles HTTP requests related to damage reports.
 * Provides CRUD functionality for damage reports.
 */
@Controller
public class DamageReportController {

    @Autowired
    Service service;

    /**
     * GET request for displaying the damage report form.
     * @return the view name for the damage report form
     */
    @GetMapping("/damageReportForm")
    public String damageReportForm() {
        return "damagereportform.html";
    }

    /**
     * POST request for deleting a damage report.
     *
     * @param wr the web request, which passes the report id, that we need to run the delete function
     * @return the view name for the damage report dashboard
     */
    @PostMapping("/deleteDamageReport")
    public String deleteDamageReport(WebRequest wr) {
        service.deleteDamageReportByID(Integer.parseInt(wr.getParameter("reportID")));
        return "redirect:/damageReportDashboard";
    }

    /**
     * POST request for editing a damage report.
     *
     * @param wr    wr the web request, which gets the report id from the clicked damage report,
     *              that we need to run the getDamageReportByID function
     * @param model the model for the view, which takes the newly gotten DamageReport instance tempDam
     *              as an attribute, that we name "damageReport",
     *              which we then access in the damagereportform.html
     * @return the view name for the damage report form
     */
    @PostMapping("/editDamageReport")
    public String editDamageReport(WebRequest wr, Model model) {
        int tempID = Integer.parseInt(wr.getParameter("reportID"));
        DamageReport tempDam = service.getDamageReportByID(tempID);
        model.addAttribute("damageReport", tempDam);

        return "damagereportform.html";
    }

    /**
     * GET request for displaying the damage report dashboard.
     * Folows the same logic as above, except it also gets the http session attribute "currentUser",
     * which is set on login to know which employee is using the program, and makes an Employee instance, tempEmp,
     * that con be retrieved later for when we set the employee id in the damagereportform.html
     * @param model    the model for the view, where we make a List of DamageReport objects called damagereports
     *                 which we then add as an attribute, so we can map through it and display the various
     *                 damage reports on our page.
     * @param employee the employee attribute
     * @param session  the HTTP session
     * @return the view name for the damage report dashboard
     */
    @GetMapping("/damageReportDashboard")
    public String getDamageReports(Model model, @ModelAttribute Employee employee, HttpSession session) {
        Employee tempEmp = (Employee) session.getAttribute("currentUser");
        model.addAttribute(tempEmp);
        List<DamageReport> damageReports = service.fetchAllDamageReports();
        model.addAttribute("damageReports", damageReports);
        return "damagereportdashboard.html";
    }

    /**
     * POST request for adding a new damage report.
     * We retrieve the info from the form, add the id of "currentUser" as well as the current date and
     * create an instance of a DamageReport object, that we then can send to our addDamageReport method.
     * @param model         the model for the view
     * @param damageReport  the damage report object
     * @param wr            the web request
     * @param session       the HTTP session
     * @return the view name for the damage report dashboard
     */
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