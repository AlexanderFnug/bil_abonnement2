package com.example.bilabonnement.Controller;

import com.example.bilabonnement.Component.BasicInfoComponent;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

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
    public String leaseForm(Model model, HttpSession session){
        List<Lease> leaseList = service.fetchAllLeases();
        List<Car> leaseableCarList = service.fetchAllCars();
        List<User> userList = service.fetchAllUsers();
        List<Employee> employeeList = service.fetchAllEmployees();
        List<String> locationList = basicInfo.getLocationList();
        //Remove already leased cars
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

        //Ugly code to get location IDs
        List<String> locationList = service.fetchAllLocations();
        String tempPickup = wr.getParameter("locationPickup");
        String tempReturn = wr.getParameter("locationReturn");
        for (int i = 0; i < locationList.size(); i++) {
            if (locationList.get(i).equals(tempPickup)){
                lease.setLocationPickupID(i+1);
            }
            if (locationList.get(i).equals(tempReturn)){
                lease.setLocationReturnID(i+1);
            }
        }

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
