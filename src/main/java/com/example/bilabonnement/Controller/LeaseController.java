package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Model.Car;
import com.example.bilabonnement.Model.Employee;
import com.example.bilabonnement.Model.Lease;
import com.example.bilabonnement.Model.User;
import com.example.bilabonnement.Service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Controller
public class LeaseController {
    @Autowired
    Service service;

    @GetMapping("/leaseDashboard")
    public String leaseDashboard(Model model){
        List<HashMap> mergedMapList = service.getMergedLeaseListAsMap();
        model.addAttribute("mergedMapList", mergedMapList);
        return "leasedashboard.html";
    }

    @GetMapping("/leaseForm")
    public String leaseForm(Model model, WebRequest wr){
        List<Lease> leaseList = service.fetchAllLeases();
        List<Car> leaseableCarList = service.fetchAllCars();
        List<User> userList = service.fetchAllUsers();
        List<Employee> employeeList = service.fetchAllEmployees();
        List<String> locationList = service.fetchAllLocations();
        //Remove already leased cars TODO: Move to service layer
        for (int i = 0; i < leaseableCarList.size(); i++) {
            for (Lease l : leaseList) {
                if (leaseableCarList.get(i).getCarID() == l.getCarID()){
                    leaseableCarList.remove(i);
                }
            }
        }
        model.addAttribute("carList",leaseableCarList);
        model.addAttribute("userList",userList);
        model.addAttribute("employeeList",employeeList);
        model.addAttribute("locationList",locationList);

        //Ugly code to calculate preset end date
        LocalDate endDate = LocalDate.now();
        switch (wr.getParameter("leaseLength")){
            case "lease3M":
                endDate = endDate.plusMonths(3);
                break;
            case "lease6M":
                endDate = endDate.plusMonths(6);
                break;
            case "leaseYear":
                endDate = endDate.plusYears(1);
                break;
        }
        model.addAttribute("leaseType",endDate);
        return "leaseform.html";
    }

    @PostMapping("/addLease")
    public String addLease(@ModelAttribute Lease lease, WebRequest wr){
        lease.setUserID(Integer.parseInt(wr.getParameter("user")));
        lease.setEmployeeID(Integer.parseInt(wr.getParameter("employee")));
        lease.setDateStart(wr.getParameter("startDate"));
        lease.setDateEnd(wr.getParameter("endDate"));
        lease.setCarID(Integer.parseInt(wr.getParameter("car")));
        lease.setMaxMileage(Integer.parseInt(wr.getParameter("maxMileage")));
        lease.setPrice(Double.parseDouble(wr.getParameter("price")));
        lease.setLocationPickupID(service.getMatchingID(wr.getParameter("locationPickup"),
                                    service.fetchAllLocations()));
        lease.setLocationReturnID(service.getMatchingID(wr.getParameter("locationReturn"),
                                    service.fetchAllLocations()));

        service.addLease(lease);
        return "leasedashboard.html";
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
